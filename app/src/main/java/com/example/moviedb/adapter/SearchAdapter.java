package com.example.moviedb.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviedb.OnMovieListener;
import com.example.moviedb.R;
import com.example.moviedb.databinding.ItemSearchBinding;
import com.example.moviedb.model.SearchResponse;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{
    private SearchResponse searchResponse;
    private OnMovieListener onMovieListener;

    public void setSearchResponse(SearchResponse searchResponse) {
        this.searchResponse = searchResponse;
        notifyDataSetChanged();
    }

    public void setOnMovieListener(OnMovieListener onMovieListener) {
        this.onMovieListener = onMovieListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSearchBinding itemSearchBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
                ,R.layout.item_search,parent,false);
        return new ViewHolder(itemSearchBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setItemSearchBinding(searchResponse.getResults().get(position));
    }

    @Override
    public int getItemCount() {
        if(searchResponse != null) return searchResponse.getResults().size();
        return 0;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{

        private ItemSearchBinding itemSearchBinding;

        public void setItemSearchBinding(SearchResponse.Result result){
            itemSearchBinding.setSearch(result);
            itemSearchBinding.executePendingBindings();
            itemSearchBinding.getRoot().setOnClickListener(v -> {
                onMovieListener.onSearchKeyword(result);
            });

        }
        public ViewHolder(@NonNull ItemSearchBinding itemSearchBinding) {
            super(itemSearchBinding.getRoot());
            this.itemSearchBinding =itemSearchBinding;
        }
    }
}
