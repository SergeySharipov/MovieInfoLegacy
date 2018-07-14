package ca.sharipov.serhii.movieinfo.ui.movielist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ca.sharipov.serhii.movieinfo.Constants;
import ca.sharipov.serhii.movieinfo.R;
import ca.sharipov.serhii.movieinfo.model.MovieBrief;
import ca.sharipov.serhii.movieinfo.model.MovieBriefListResponse;
import ca.sharipov.serhii.movieinfo.utils.SharedPrefUtil;
import timber.log.Timber;

public class MovieListFragment extends Fragment
        implements MovieListContract.View {
    private static final String TAG = "MovieListFragment";
    protected boolean mIsLoading = false;
    protected boolean mIsLastPage = false;
    private TextView mEmptyTextView;
    private MovieListAdapter mMovieListAdapter;
    private GridLayoutManager mLayoutManager;
    private MovieListContract.Presenter mPresenter;
    protected final RecyclerView.OnScrollListener recyclerViewOnScrollListener =
            new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    if (mPresenter != null && mLayoutManager != null) {
                        int visibleItemCount = mLayoutManager.getChildCount();
                        int totalItemCount = mLayoutManager.getItemCount();
                        int firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition();

                        if (!mIsLoading && !mIsLastPage) {
                            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                                    && firstVisibleItemPosition >= 0
                                    && totalItemCount >= Constants.PAGE_ITERATOR_SIZE) {
                                loadNext();
                            }
                        }
                    }
                }
            };

    public static MovieListFragment newInstance() {
        return new MovieListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);

        View.OnClickListener mRetryButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadNext();
            }
        };

        mMovieListAdapter = new MovieListAdapter(this, mRetryButtonClickListener);
        mPresenter = new MovieListPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.fragment_movie_list_recycler_view);
        mEmptyTextView = view.findViewById(R.id.fragment_movie_list_text_view);

        int cols = getResources().getInteger(R.integer.fragment_movie_list_rv_cols);
        mLayoutManager = new GridLayoutManager(getContext(), cols);
        mLayoutManager.setSpanSizeLookup(getSpanSizeLookup(cols));
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mMovieListAdapter);
        recyclerView.addOnScrollListener(recyclerViewOnScrollListener);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter.takeView(this);

        loadNext();
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.takeView(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.dropView();
    }

    @Override
    public void onLoadingStart() {
        mIsLoading = true;
    }

    @Override
    public void onStartNewLoad() {
        mEmptyTextView.setVisibility(View.GONE);
        mMovieListAdapter.clearMovieBriefs();
        mMovieListAdapter.setLastPage(false);
    }

    @Override
    public void onLoadComplete() {
        mIsLoading = false;
        mMovieListAdapter.setError(false);
    }

    @Override
    public void onError(Throwable e) {
        Timber.e(e);
        mMovieListAdapter.setError(true);
        mMovieListAdapter.notifyLastElementChanged();
    }

    @Override
    public void onItemsLoaded(MovieBriefListResponse movieBriefListResponse, boolean hasMoreData) {
        mIsLastPage = !hasMoreData;
        List<MovieBrief> movieBriefs = movieBriefListResponse.getResults();
        Timber.d("Loaded %d items.", movieBriefs.size());

        int itemCount = mMovieListAdapter.getItemCount();

        mMovieListAdapter.setLastPage(mIsLastPage);
        mMovieListAdapter.addMovieBriefs(movieBriefs);

        if (mMovieListAdapter.getItemCount() > 0) {
            SharedPrefUtil.setLastResult(getContext(), mMovieListAdapter.getLastMovieId());
            mEmptyTextView.setVisibility(View.GONE);
            mMovieListAdapter.notifyItemRangeChanged(itemCount,
                    itemCount + movieBriefs.size() - 1);
            //mMovieListAdapter.notifyDataSetChanged();
        } else {
            mEmptyTextView.setVisibility(View.VISIBLE);
        }
    }

    private void loadNext() {
        String storedQuery = SharedPrefUtil.getStoredQuery(getActivity());
        if (storedQuery == null) {
            mPresenter.loadMovies();
        } else {
            mPresenter.search(storedQuery);
        }
    }

    @NonNull
    private GridLayoutManager.SpanSizeLookup getSpanSizeLookup(final int spanCount) {
        return new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int viewType = mMovieListAdapter.getItemViewType(position);
                return viewType != 0 ? spanCount : 1;
            }
        };
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(R.menu.fragment_movie_list, menu);

        MenuItem searchItem = menu.findItem(R.id.menu_item_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Timber.tag(TAG).d("QueryTextSubmit: " + s);
                mPresenter.reset();
                mPresenter.search(s);
                SharedPrefUtil.setStoredQuery(getActivity(), s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Timber.tag(TAG).d("QueryTextChange: " + s);
                return false;
            }
        });

        String storedQuery = SharedPrefUtil.getStoredQuery(getActivity());
        searchView.setQuery(storedQuery, false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_clear:
                SharedPrefUtil.setStoredQuery(getActivity(), null);
                mPresenter.reset();
                mPresenter.loadMovies();
                return true;
            case R.id.menu_refresh:
                mPresenter.reset();
                loadNext();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}