package com.example.moviedb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviedb.OnMovieListener;
import com.example.moviedb.R;
import com.example.moviedb.databinding.ItemMovieBinding;
import com.example.moviedb.model.MovieResponse;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private MovieResponse movieResponse ;
    private Context context;
    private OnMovieListener onMovieListener;

    public MovieAdapter(MovieResponse movieResponse, OnMovieListener onMovieListener) {
        this.movieResponse = movieResponse;
        this.onMovieListener = onMovieListener;
    }

    public MovieAdapter(Context context) {
        this.context = context;
    }

    public void setOnMovieListener(OnMovieListener listener) {
        this.onMovieListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMovieBinding itemMovieBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_movie, parent, false);
        return new ViewHolder(itemMovieBinding);
    }

    public void setMovieResponse(MovieResponse movieResponse) {
        this.movieResponse = movieResponse;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setBinding(movieResponse.getMovies().get(position));
    }

    @Override
    public int getItemCount() {
        if (movieResponse != null) return movieResponse.getMovies().size();
        else return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ItemMovieBinding itemMovieBinding;
        public void setBinding(MovieResponse.Movie movie){
            itemMovieBinding.setMovie(movie);
            itemMovieBinding.executePendingBindings();
            itemMovieBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public
                void onClick(View v) {
                    if(onMovieListener == null) return;
                    onMovieListener.onMovieClick(movie);
                }
            });
        }

        public ViewHolder(ItemMovieBinding itemMovieBinding) {
            super(itemMovieBinding.getRoot());
            this.itemMovieBinding=itemMovieBinding;
        }
    }

}
