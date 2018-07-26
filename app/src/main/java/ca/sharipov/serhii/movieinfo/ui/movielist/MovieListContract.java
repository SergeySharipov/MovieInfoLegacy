package ca.sharipov.serhii.movieinfo.ui.movielist;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ca.sharipov.serhii.movieinfo.model.MovieBriefListResponse;
import ca.sharipov.serhii.movieinfo.ui.BasePresenter;

class MovieListContract {

    interface View {
        void onLoadingStart();

        void onStartNewLoad();

        void onLoadComplete();

        void onError(Throwable e);

        void onItemsLoaded(@NonNull MovieBriefListResponse movieBriefListResponse, boolean hasMoreData);
    }

    interface Presenter extends BasePresenter<View> {
        void loadMovies();

        void search(@Nullable String query);

        void reset();
    }

}
