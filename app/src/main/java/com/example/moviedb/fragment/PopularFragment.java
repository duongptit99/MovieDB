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
import com.example.moviedb.databinding.FragmentPopularBinding;
import com.example.moviedb.model.MovieResponse;
import com.example.moviedb.model.SearchResponse;
import com.example.moviedb.viewmodel.PoPularViewModel;

public class PopularFragment extends Fragment implements OnMovieListener {
    private PoPularViewModel poPularViewModel;

    private MovieAdapter adapter;

    private FragmentPopularBinding fragmentPopularBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentPopularBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_popular,container,false);
        return fragmentPopularBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new MovieAdapter(requireContext());
        adapter.setOnMovieListener(this);
        poPularViewModel = new ViewModelProvider(this).get(PoPularViewModel.class);
        fragmentPopularBinding.recyclerviewPopular.setAdapter(adapter);
        poPularViewModel.getPopular().observe((LifecycleOwner) getContext(), o -> {
            if( o == null ) return;
            adapter.setMovieResponse((MovieResponse) o);
        });
        poPularViewModel.fetchPopular();

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