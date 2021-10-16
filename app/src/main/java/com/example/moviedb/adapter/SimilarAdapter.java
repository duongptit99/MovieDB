package com.example.moviedb.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviedb.OnMovieListener;
import com.example.moviedb.R;
import com.example.moviedb.databinding.ItemSimilarBinding;
import com.example.moviedb.model.MovieResponse;
import com.example.moviedb.model.SimilarResponse;

public class SimilarAdapter extends RecyclerView.Adapter<SimilarAdapter.ViewHolder>{
    private SimilarResponse similarResponse;
    private OnMovieListener onMovieListener;

    public void setSimilarReponse(SimilarResponse similarResponse) {
        this.similarResponse = similarResponse;
        notifyDataSetChanged();
    }

    public void setOnMovieListener(OnMovieListener onMovieListener) {
        this.onMovieListener = onMovieListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSimilarBinding itemSimilarBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext())
                    ,R.layout.item_similar,parent,false);
        return new ViewHolder(itemSimilarBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setItemSimilarBinding(similarResponse.getResults().get(position));
    }

    @Override
    public int getItemCount() {
        if (similarResponse == null) return 0;
        return similarResponse.getResults().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ItemSimilarBinding itemSimilarBinding;

        public void setItemSimilarBinding(MovieResponse.Movie movie){
            itemSimilarBinding.setMovie(movie);
            itemSimilarBinding.executePendingBindings();
            itemSimilarBinding.getRoot().setOnClickListener(v -> {
                onMovieListener.onMovieClick(movie);
            });
        }

        public ViewHolder(ItemSimilarBinding itemSimilarBinding) {
            super(itemSimilarBinding.getRoot());
            this.itemSimilarBinding = itemSimilarBinding;
        }
    }
}
