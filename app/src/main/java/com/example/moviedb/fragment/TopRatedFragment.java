package com.example.moviedb.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviedb.activity.DetailActivity;
import com.example.moviedb.adapter.MovieAdapter;
import com.example.moviedb.OnMovieListener;
import com.example.moviedb.R;
import com.example.moviedb.databinding.FragmentTopRatedBinding;
import com.example.moviedb.model.MovieResponse;
import com.example.moviedb.model.SearchResponse;
import com.example.moviedb.viewmodel.TopRatedViewModel;

public class TopRatedFragment extends Fragment implements OnMovieListener {
    private MovieAdapter movieAdapter;
    private FragmentTopRatedBinding fragmentTopRatedBinding;
    private TopRatedViewModel topRatedViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentTopRatedBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_top_rated,container,false);
        return fragmentTopRatedBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        movieAdapter = new MovieAdapter(requireContext());
        movieAdapter.setOnMovieListener(this);
        topRatedViewModel = new ViewModelProvider(this).get(TopRatedViewModel.class);
        fragmentTopRatedBinding.recyclerviewToprated.setAdapter(movieAdapter);
        topRatedViewModel.getTopRated().observe((LifecycleOwner) getContext(), o -> {
            if(o==null) return;
            movieAdapter.setMovieResponse((MovieResponse) o);
        });

        topRatedViewModel.fetchTopRated();
    }

    @Override
    public void onMovieClick(MovieResponse.Movie movie) {
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra("movie_id", movie.getId());
        startActivity(intent);
    }

    @Override
    public void onSearchKeyword(SearchResponse.Result result) {

    }
}