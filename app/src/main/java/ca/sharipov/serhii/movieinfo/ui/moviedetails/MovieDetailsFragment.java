package ca.sharipov.serhii.movieinfo.ui.moviedetails;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    private TextView mVoteAverageTextView;
    private RecyclerView genresRv;
    private ImageView imagePoster;

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
        mVoteAverageTextView = view.findViewById(R.id.vote_average);

        genresRv = view.findViewById(R.id.genres_list);

        imagePoster = view.findViewById(R.id.image_poster);

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
        mReleaseDateTextView.setText("Release date: " + movie.getReleaseDate());
        mVoteAverageTextView.setText("Rating: " + movie.getVoteAverage() + "(" + movie.getVoteCount() + ")");

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        genresRv.setLayoutManager(layoutManager);
        genresRv.setAdapter(new GenresRecyclerViewAdapter(movie.getGenres()));

        ImageUtil.loadImage(getContext(), imagePoster, movie.getPosterLink());
    }
}