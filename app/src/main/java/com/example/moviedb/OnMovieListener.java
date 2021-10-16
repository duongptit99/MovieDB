package com.example.moviedb;

import com.example.moviedb.model.MovieResponse;
import com.example.moviedb.model.SearchResponse;

public interface OnMovieListener {
    void onMovieClick(MovieResponse.Movie movie);
    void onSearchKeyword(SearchResponse.Result result);
}
