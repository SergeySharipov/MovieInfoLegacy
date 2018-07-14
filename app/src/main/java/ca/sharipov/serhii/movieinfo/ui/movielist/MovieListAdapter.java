package ca.sharipov.serhii.movieinfo.ui.movielist;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ca.sharipov.serhii.movieinfo.R;
import ca.sharipov.serhii.movieinfo.model.MovieBrief;
import ca.sharipov.serhii.movieinfo.ui.holders.LoadMoreHolder;
import ca.sharipov.serhii.movieinfo.ui.holders.MovieHolder;

public class MovieListAdapter extends RecyclerView.Adapter<LoadMoreHolder<MovieBrief>>
        implements View.OnClickListener {
    private static final int VIEW_TYPE_LOAD_MORE = 101;
    private static final int VIEW_TYPE_RETRY = 102;

    private List<MovieBrief> mMovieBriefs;
    private Fragment mFragment;

    private View.OnClickListener mOnRetryButtonClickListener;

    private boolean mIsLastPage = false;
    private boolean mIsError = false;

    MovieListAdapter(Fragment fragment, final View.OnClickListener onRetryButtonClickListener) {
        mMovieBriefs = new ArrayList<>();
        mFragment = fragment;
        mOnRetryButtonClickListener = onRetryButtonClickListener;
    }

    public void setMovieBriefs(List<MovieBrief> movieBriefs) {
        mMovieBriefs = movieBriefs;
    }

    public void addMovieBriefs(List<MovieBrief> movieBriefs) {
        mMovieBriefs.addAll(movieBriefs);
    }

    @NonNull
    @Override
    public LoadMoreHolder<MovieBrief> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mFragment.getContext());
        View view;

        if (viewType == VIEW_TYPE_RETRY) {
            view = inflater.inflate(R.layout.item_retry, parent, false);
            return new LoadMoreHolder<>(view, this);
        } else if (viewType == VIEW_TYPE_LOAD_MORE) {
            view = inflater.inflate(R.layout.item_loadmore, parent, false);
            return new LoadMoreHolder<>(view);
        } else {
            view = inflater.inflate(R.layout.item_movie, parent, false);
            return new MovieHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull LoadMoreHolder<MovieBrief> holder, int position) {
        if (getItemViewType(position) == 0) {
            holder.onBind(mFragment, mMovieBriefs.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (!mIsLastPage && position == getItemCount() - 1) {
            if (mIsError) {
                return VIEW_TYPE_RETRY;
            } else {
                return VIEW_TYPE_LOAD_MORE;
            }
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        int itemCount = mMovieBriefs.size();
        return !mIsLastPage ? itemCount + 1 : itemCount;
    }

    public String getLastMovieId() {
        return mMovieBriefs.size() > 0 ? mMovieBriefs.get(0).getId() + "" : "";
    }

    public boolean isLastPage() {
        return mIsLastPage;
    }

    public void setLastPage(boolean lastPage) {
        mIsLastPage = lastPage;
    }

    public boolean isError() {
        return mIsError;
    }

    public void setError(boolean error) {
        mIsError = error;
    }

    @Override
    public void onClick(View v) {
        mIsError = false;
        if (mOnRetryButtonClickListener != null) {
            mOnRetryButtonClickListener.onClick(v);
        }
        notifyLastElementChanged();
    }

    public void notifyLastElementChanged() {
        this.notifyItemChanged(getItemCount() - 1);
    }

    public void clearMovieBriefs() {
        mMovieBriefs.clear();
        this.notifyDataSetChanged();
    }
}
