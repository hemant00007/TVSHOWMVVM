package com.example.tvshow.response;

import com.example.tvshow.models.TvShow;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvShowResponse {

    @SerializedName("page")
    int page;
    @SerializedName("pages")
    private int totalpages;
    @SerializedName("tv_shows")
    private List<TvShow> tvShows;

    public int getPage() {
        return page;
    }

    public int getTotalpages() {
        return totalpages;
    }

    public List<TvShow> getTvShows() {
        return tvShows;
    }
}
