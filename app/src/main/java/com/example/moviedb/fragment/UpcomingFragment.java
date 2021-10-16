package com.example.moviedb.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviedb.activity.DetailActivity;
import com.example.moviedb.adapter.MovieAdapter;
import com.example.moviedb.OnMovieListener;
import com.example.moviedb.R;
import com.example.moviedb.databinding.FragmentUpcomingBinding;
import com.example.moviedb.model.MovieResponse;
import com.example.moviedb.model.SearchResponse;
import com.example.moviedb.viewmodel.UpcomingViewModel;

public class UpcomingFragment extends Fragment implements OnMovieListener {

    private FragmentUpcomingBinding fragmentUpcomingBinding;
    private MovieAdapter movieAdapter;
    private UpcomingViewModel upcomingViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentUpcomingBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_upcoming, container, false);
        return fragmentUpcomingBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        movieAdapter = new MovieAdapter(requireContext());
        movieAdapter.setOnMovieListener(this);
        upcomingViewModel = new ViewModelProvider(this).get(UpcomingViewModel.class);
        fragmentUpcomingBinding.recyclerviewUpcoming.setAdapter(movieAdapter);
        upcomingViewModel.getUpcoming().observe((LifecycleOwner) getContext(), new Observer() {
            @Override
            public void onChanged(Object o) {
                if (o == null) return;
                movieAdapter.setMovieResponse((MovieResponse) o);
            }
        });
        upcomingViewModel.fetchUpcoming();

    }

    @Override
    public void onMovieClick(MovieResponse.Movie movie) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("movie_id", movie.getId());
        startActivity(intent);
    }

    @Override
    public void onSearchKeyword(SearchResponse.Result result) {
        
    }
}