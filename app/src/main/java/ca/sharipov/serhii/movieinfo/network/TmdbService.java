package ca.sharipov.serhii.movieinfo.network;

import ca.sharipov.serhii.movieinfo.model.Movie;
import ca.sharipov.serhii.movieinfo.model.MovieBriefListResponse;
import ca.sharipov.serhii.movieinfo.utils.RetrofitUtil;
import retrofit2.Response;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TmdbService {
    private TmdbApi mTmdbApi;

    private TmdbService() {
        mTmdbApi = RetrofitUtil.createService(TmdbApi.class);
    }

    public static TmdbService getInstance() {
        return Nested.instance;
    }

    public void getMovie(Observer<Response<Movie>> observer, int externalId) {
        mTmdbApi.getMovie(externalId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getPopularMovies(Observer<Response<MovieBriefListResponse>> observer, int page) {
        mTmdbApi.getPopularMovies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void searchMovieByName(Observer<Response<MovieBriefListResponse>> observer, String movieName, int page) {
        mTmdbApi.searchMovieByName(movieName, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    private static class Nested {
        static TmdbService instance = new TmdbService();
    }

}
