package com.example.tvshow.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tvshow.network.ApiClient;
import com.example.tvshow.network.ApiService;
import com.example.tvshow.response.TvShowResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MostPopularTVShowRepository {
    private ApiService apiService;

    public MostPopularTVShowRepository(){
        apiService= ApiClient.getRetrofit().create(ApiService.class);

    }
    public LiveData<TvShowResponse> getMostPopularTVShows(int page){
        MutableLiveData<TvShowResponse> data = new MutableLiveData<>();
        apiService.getMostPopularTVShows(page).enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(Call<TvShowResponse> call, @NonNull Response<TvShowResponse> response) {
                data.setValue(response.body());

            }

            @Override
            public void onFailure(@NonNull Call<TvShowResponse> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
