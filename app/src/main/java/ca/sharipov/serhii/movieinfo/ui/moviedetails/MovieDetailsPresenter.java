package ca.sharipov.serhii.movieinfo.ui.moviedetails;

import ca.sharipov.serhii.movieinfo.model.Movie;
import ca.sharipov.serhii.movieinfo.network.TmdbService;
import retrofit2.Response;
import rx.Subscriber;

public class MovieDetailsPresenter implements MovieDetailsContract.Presenter {
    private Subscriber<Response<Movie>> mSubscriber;
    private MovieDetailsContract.View mView;
    private int mMovieId;

    MovieDetailsPresenter(int movieId) {
        mMovieId = movieId;
    }

    private void createSubscriber() {
        unsubscribe();

        mSubscriber = new Subscriber<Response<Movie>>() {
            @Override
            public void onCompleted() {
                mView.onLoadComplete();
            }

            @Override
            public void onError(Throwable e) {
                mView.onError(e);
            }

            @Override
            public void onNext(Response<Movie> response) {
                if (response.isSuccessful()) {
                    Movie movie = response.body();
                    if (movie != null) {
                        mView.onMovieLoaded(movie);
                    }
                }
            }
        };
    }

    @Override
    public void loadMovie() {
        createSubscriber();
        mView.onLoadingStart();
        TmdbService.getInstance()
                .getMovie(mSubscriber, mMovieId);
    }

    @Override
    public void takeView(MovieDetailsContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        unsubscribe();
        mView = null;
    }

    private void unsubscribe() {
        if (mSubscriber != null && mSubscriber.isUnsubscribed()) {
            mSubscriber.unsubscribe();
        }
    }
}
