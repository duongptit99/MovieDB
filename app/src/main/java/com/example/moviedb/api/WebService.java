package com.example.moviedb.api;

import com.example.moviedb.model.MovieResponse;
import com.example.moviedb.model.SimilarResponse;
import com.example.moviedb.model.TrailerResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WebService {

    @GET("movie/now_playing")
    Observable<MovieResponse> getNowPlaying(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Observable<MovieResponse> getPopular(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Observable<MovieResponse> getTopRated(@Query("api_key") String apiKey);

    @GET("movie/upcoming")
    Observable<MovieResponse> getUpComing(@Query("api_key") String apiKey);

    @GET("movie/{movie_id}")
    Observable<MovieResponse.Movie> getMovieDetail(@Path ("movie_id") int id ,@Query("api_key") String apiKey);

    @GET("movie/{movie_id}/videos")
    Observable<TrailerResponse> getVideo(@Path("movie_id") int id , @Query("api_key") String apiKey);

    @GET("movie/{movie_id}/similar")
    Observable<SimilarResponse> getSimilarVideo(@Path("movie_id") int id , @Query("api_key") String apiKey);

    @GET("movie/{movie_id}/recommendations")
    Observable<MovieResponse.Movie> getRecommendation(@Path("movie_id") int id , @Query("api_key") String apiKey);

    @GET("search/keyword")
    Observable<MovieResponse.Movie> getSearchKeyWord(@Path("search") String id, @Query("api_key") String apikey);
}
