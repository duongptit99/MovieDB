package com.example.moviedb.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import com.example.moviedb.activity.DetailActivity;
import com.example.moviedb.adapter.MovieAdapter;
import com.example.moviedb.OnMovieListener;
import com.example.moviedb.R;
import com.example.moviedb.databinding.FragmentNowPlayingBinding;
import com.example.moviedb.model.MovieResponse;
import com.example.moviedb.model.SearchResponse;
import com.example.moviedb.viewmodel.NowPlayingViewModel;


public class NowPlayingFragment extends Fragment implements OnMovieListener {

    private NowPlayingViewModel nowPlayingViewModel;
    private MovieAdapter movieAdapter;
    private FragmentNowPlayingBinding fragmentNowplayingBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentNowplayingBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_now_playing, container, false);
        fragmentNowplayingBinding.setLifecycleOwner(this);
        return fragmentNowplayingBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        movieAdapter = new MovieAdapter(requireContext());
        movieAdapter.setOnMovieListener(this);
        nowPlayingViewModel = new ViewModelProvider(this).get(NowPlayingViewModel.class);
        fragmentNowplayingBinding.recyclerviewNowplaying.setAdapter(movieAdapter);
        nowPlayingViewModel.getNowPlaying().observe((LifecycleOwner) getContext(), o -> {
            if (o == null) return;
            movieAdapter.setMovieResponse((MovieResponse) o);
        });
        nowPlayingViewModel.fetchNowPlaying();
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