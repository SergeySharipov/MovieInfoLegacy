package ca.sharipov.serhii.movieinfo.ui.moviedetails;

import android.support.annotation.NonNull;

import ca.sharipov.serhii.movieinfo.model.Movie;
import ca.sharipov.serhii.movieinfo.ui.BasePresenter;

class MovieDetailsContract {

    interface View {
        void onLoadingStart();

        void onLoadComplete();

        void onError(Throwable e);

        void onMovieLoaded(@NonNull Movie movie);
    }

    interface Presenter extends BasePresenter<View> {
        void loadMovie();
    }

}
