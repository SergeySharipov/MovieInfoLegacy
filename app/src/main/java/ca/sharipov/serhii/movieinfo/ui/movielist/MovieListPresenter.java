package ca.sharipov.serhii.movieinfo.ui.movielist;

import android.support.annotation.Nullable;

import ca.sharipov.serhii.movieinfo.model.MovieBriefListResponse;
import ca.sharipov.serhii.movieinfo.network.TmdbService;
import retrofit2.Response;
import rx.Subscriber;

public class MovieListPresenter implements MovieListContract.Presenter {
    private Subscriber<Response<MovieBriefListResponse>> mSubscriber;
    private MovieListContract.View mView;

    private int mPage = 1;

    private void createSubscriber() {
        unsubscribe();

        mSubscriber = new Subscriber<Response<MovieBriefListResponse>>() {
            @Override
            public void onCompleted() {
                mPage++;
                mView.onLoadComplete();
            }

            @Override
            public void onError(Throwable e) {
                mView.onError(e);
            }

            @Override
            public void onNext(Response<MovieBriefListResponse> response) {
                if (response.isSuccessful()) {
                    MovieBriefListResponse body = response.body();
                    if (body != null) {
                        int pages = body.getTotalPages();
                        boolean hasMoreData = mPage < pages;
                        mView.onItemsLoaded(body, hasMoreData);
                    }
                }
            }
        };
    }

    @Override
    public void loadMovies() {
        createSubscriber();
        mView.onLoadingStart();
        TmdbService.getInstance()
                .getPopularMovies(mSubscriber, mPage);
    }

    @Override
    public void search(@Nullable String query) {
        if (query != null) {
            createSubscriber();
            mView.onLoadingStart();
            TmdbService.getInstance()
                    .searchMovieByName(mSubscriber, query, mPage);
        }
    }

    @Override
    public void reset() {
        mPage = 1;
        mView.onStartNewLoad();
        loadMovies();
    }

    @Override
    public void takeView(MovieListContract.View view) {
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
