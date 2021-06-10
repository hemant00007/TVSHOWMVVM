package com.example.tvshow.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tvshow.R;
import com.example.tvshow.databinding.ItemContainerTvShowBinding;
import com.example.tvshow.models.TvShow;

import java.util.List;

public class TvShowsAdapter extends RecyclerView.Adapter<TvShowsAdapter.TVShowViewholder> {

    private List<TvShow> tvShows;
    private LayoutInflater layoutInflater;

    public TvShowsAdapter(List<TvShow> tvShows) {
        this.tvShows = tvShows;
    }

    @NonNull
    @Override
    public TVShowViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater ==null){
            layoutInflater= LayoutInflater.from(parent.getContext());
        }
        ItemContainerTvShowBinding tvShowBinding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_container_tv_show,parent,false
        );
        return new TVShowViewholder(tvShowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TVShowViewholder holder, int position) {

        holder.bindTvshow(tvShows.get(position));
    }

    @Override
    public int getItemCount() {
        return tvShows.size();
    }

    static class TVShowViewholder extends RecyclerView.ViewHolder{
        private ItemContainerTvShowBinding itemContainerTvShowBinding;



        public TVShowViewholder(ItemContainerTvShowBinding itemContainerTvShowBinding){
            super(itemContainerTvShowBinding.getRoot());
            this.itemContainerTvShowBinding=itemContainerTvShowBinding;
        }
        public void bindTvshow(TvShow tvShow){
            itemContainerTvShowBinding.setTvshow(tvShow);
            itemContainerTvShowBinding.executePendingBindings();
        }
    }
}
