package ca.sharipov.serhii.movieinfo.network;

import ca.sharipov.serhii.movieinfo.model.MovieBriefListResponse;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface TmdbApi {

    @GET("3/discover/movie?language=en&sort_by=popularity.desc")
    Observable<Response<MovieBriefListResponse>> getPopularMovies(@Query("page") int page);

    @GET("3/search/movie")
    Observable<Response<MovieBriefListResponse>> searchMovieByName(@Query("query") String movieName,
                                                                   @Query("page") int page);

}
