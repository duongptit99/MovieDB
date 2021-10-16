package com.example.moviedb.reponsitory;

import com.example.moviedb.api.WebService;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {
    private WebService service;

    public Repository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(WebService.class);
    }

    public Observable getNowPlaying() {
        return service.getNowPlaying("4b6dbb466a7dcc286e614fe5d5845299");
    }

    public Observable getPopular(){
        return service.getPopular("4b6dbb466a7dcc286e614fe5d5845299");
    }

    public Observable getTopRated(){
        return service.getTopRated("4b6dbb466a7dcc286e614fe5d5845299");
    }

    public Observable getUpcoming(){
        return service.getUpComing("4b6dbb466a7dcc286e614fe5d5845299");
    }

    public Observable getVideo(int movie_id){
        return service.getVideo(movie_id,"4b6dbb466a7dcc286e614fe5d5845299");
    }

    public Observable getDetail(int movie_id){
        return service.getMovieDetail(movie_id,"4b6dbb466a7dcc286e614fe5d5845299");
    }

    public Observable getSimilar(int movie_id){
        return service.getSimilarVideo(movie_id,"4b6dbb466a7dcc286e614fe5d5845299");
    }
}
