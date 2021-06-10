package com.example.tvshow.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.tvshow.repositories.MostPopularTVShowRepository;
import com.example.tvshow.response.TvShowResponse;

public class MostPopularViewModels  extends ViewModel {
    private MostPopularTVShowRepository mostPopularTVShowRepository;

    public MostPopularViewModels(){
        mostPopularTVShowRepository = new MostPopularTVShowRepository();
    }
    public LiveData<TvShowResponse> getMostPopularTVShows(int page){
        return mostPopularTVShowRepository.getMostPopularTVShows(page);
    }
}
