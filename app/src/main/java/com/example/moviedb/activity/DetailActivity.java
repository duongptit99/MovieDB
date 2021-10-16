package com.example.moviedb.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.moviedb.OnMovieListener;
import com.example.moviedb.R;
import com.example.moviedb.adapter.SimilarAdapter;
import com.example.moviedb.databinding.ActivityDetailBinding;
import com.example.moviedb.model.MovieResponse;
import com.example.moviedb.model.SearchResponse;
import com.example.moviedb.model.TrailerResponse;
import com.example.moviedb.viewmodel.DetailViewModel;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;

public class DetailActivity extends AppCompatActivity implements OnMovieListener{
    private ActivityDetailBinding binding;
    private DetailViewModel detailViewModel;
    private MovieResponse movieResponse;
    private SimilarAdapter similarAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        int id = intent.getIntExtra("movie_id",0);


        binding = DataBindingUtil.setContentView(this,R.layout.activity_detail);
        detailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);

        //detail movie
        detailViewModel.fetchDetailMovie(id).observe(this, movie -> {
            Glide.with(this).load("https://image.tmdb.org/t/p/w500" + movie.getPosterPath())
                    .into(binding.imageDetailPoster);
            binding.textDetailName.setText(movie.getTitle());
            binding.textIntroduce.setText(movie.getOverview());
        });

        //detail trailer
        detailViewModel.fetchDetailVideo(id).observe(this, trailer -> {
            binding.youtube.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    super.onReady(youTubePlayer);
                    for(TrailerResponse.Trailer a : trailer.getResults()) {
                        youTubePlayer.loadVideo(a.getKey(), 0);
                    }
                }
            });
        });

        //detail similar
        detailViewModel.fetchDetailSimilar(id).observe(this,similarReponse -> {
            similarAdapter = new SimilarAdapter();
            similarAdapter.setSimilarReponse(similarReponse);
            similarAdapter.setOnMovieListener((OnMovieListener) this);
            binding.recyclerViewSimilar.setAdapter(similarAdapter);
        });

    }

    @Override
    public void onMovieClick(MovieResponse.Movie movie) {
        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
        intent.putExtra("movie_id", movie.getId());
        startActivity(intent);
    }

    @Override
    public void onSearchKeyword(SearchResponse.Result result) {

    }
}