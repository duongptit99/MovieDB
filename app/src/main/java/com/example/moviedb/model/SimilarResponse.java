package com.example.moviedb.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SimilarResponse {
    @SerializedName("page")
    private int page;

    @SerializedName("results")
    private List<MovieResponse.Movie> results;

    @SerializedName("total_pages")
    private int total_pages;

    @SerializedName("total_results")
    private int total_results;

    public
    int getPage() {
        return page;
    }

    public
    List<MovieResponse.Movie> getResults() {
        return results;
    }

    public
    int getTotal_pages() {
        return total_pages;
    }

    public
    int getTotal_results() {
        return total_results;
    }
}
