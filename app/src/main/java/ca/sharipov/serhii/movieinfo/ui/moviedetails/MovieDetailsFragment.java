package ca.sharipov.serhii.movieinfo.ui.moviedetails;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ca.sharipov.serhii.movieinfo.R;
import ca.sharipov.serhii.movieinfo.model.Movie;
import ca.sharipov.serhii.movieinfo.utils.ImageUtil;

public class MovieDetailsFragment extends Fragment implements MovieDetailsContract.View {
    public static final String MOVIE_ID = "MOVIE_ID";
    private TextView mTitleTextView;
    private TextView mOverviewTextView;
    private TextView mReleaseDateTextView;
    private TextView mAdultTextView;
    private TextView mOriginalLanguageTextView;
    private TextView mOriginalTitleTextView;
    private TextView mBudgetTextView;
    private TextView mHomepageTextView;
    private TextView mRevenueTextView;
    private TextView mRuntimeTextView;
    private TextView mStatusTextView;
    private TextView mTaglineTextView;
    private TextView mVoteAverageTextView;

    private MovieDetailsContract.Presenter mPresenter;

    public MovieDetailsFragment() {
        // Required empty public constructor
    }

    public static MovieDetailsFragment newInstance(int movieId) {
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        Bundle args = new Bundle();
        args.putInt(MOVIE_ID, movieId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            int movieId = getArguments().getInt(MOVIE_ID);
            mPresenter = new MovieDetailsPresenter(movieId);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_details, container, false);

        mTitleTextView = view.findViewById(R.id.title);
        mOverviewTextView = view.findViewById(R.id.overview);
        mReleaseDateTextView = view.findViewById(R.id.release_date);
        mAdultTextView = view.findViewById(R.id.adult);
        mOriginalLanguageTextView = view.findViewById(R.id.original_language);
        mOriginalTitleTextView = view.findViewById(R.id.originalTitle);
        mBudgetTextView = view.findViewById(R.id.budget);
        mHomepageTextView = view.findViewById(R.id.homepage);
        mRevenueTextView = view.findViewById(R.id.revenue);
        mRuntimeTextView = view.findViewById(R.id.runtime);
        mStatusTextView = view.findViewById(R.id.status);
        mTaglineTextView = view.findViewById(R.id.tagline);
        mVoteAverageTextView = view.findViewById(R.id.vote_average);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter.takeView(this);

        mPresenter.loadMovie();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mPresenter.dropView();
    }

    @Override
    public void onLoadingStart() {

    }

    @Override
    public void onLoadComplete() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onMovieLoaded(@NonNull Movie movie) {
        mTitleTextView.setText(movie.getTitle());
        mOverviewTextView.setText(movie.getOverview());
        mReleaseDateTextView.setText(movie.getReleaseDate());
        mAdultTextView.setText(movie.isAdult() + "");
        mAdultTextView.setText(movie.getGenres().get(0) + "");
        mOriginalLanguageTextView.setText(movie.getOriginalLanguage());
        mOriginalTitleTextView.setText(movie.getOriginalTitle());
        mBudgetTextView.setText(movie.getBudget() + "");
        mHomepageTextView.setText(movie.getHomepage());
        mRevenueTextView.setText(movie.getRevenue() + "");
        mRuntimeTextView.setText(movie.getRuntime() + "");
        mStatusTextView.setText(movie.getStatus());
        mTaglineTextView.setText(movie.getTagline());
        mVoteAverageTextView.setText(movie.getVoteAverage() + "(" + movie.getVoteCount() + ")");

        ImageUtil.loadImage(getContext(), (ImageView) getView().findViewById(R.id.image_poster), movie.getPosterLink());
    }
}
