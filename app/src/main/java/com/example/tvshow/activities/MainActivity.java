package com.example.tvshow.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.tvshow.R;
import com.example.tvshow.adapters.TvShowsAdapter;
import com.example.tvshow.databinding.ActivityMainBinding;
import com.example.tvshow.models.TvShow;
import com.example.tvshow.viewModels.MostPopularViewModels;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MostPopularViewModels viewModels;
private ActivityMainBinding activityMainBinding;
private List<TvShow> tvShows = new ArrayList<>();
private TvShowsAdapter tvShowsAdapter;
private int currentPage = 1;
private int totalAvailablepage = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        doInitilization();
    }
    private void doInitilization(){
        activityMainBinding.tvshowRecyclerview.setHasFixedSize(true);
        viewModels = new ViewModelProvider(this).get(MostPopularViewModels.class);
        tvShowsAdapter= new TvShowsAdapter(tvShows);
        activityMainBinding.tvshowRecyclerview.setAdapter(tvShowsAdapter);
        activityMainBinding.tvshowRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!activityMainBinding.tvshowRecyclerview.canScrollVertically(1)){
                    if (currentPage <= totalAvailablepage){
                        currentPage+=1;
                        getMostPopularTVShows();
                    }

                }
            }
        });
        getMostPopularTVShows();
    }
    private void getMostPopularTVShows(){
       toogleLoading();
        viewModels.getMostPopularTVShows(currentPage).observe(this,mostPopularTVShowsResponse ->{
//
            toogleLoading();
            if (mostPopularTVShowsResponse !=null){
                totalAvailablepage= mostPopularTVShowsResponse.getTotalpages();
                if (mostPopularTVShowsResponse.getTvShows() !=null){
                    int oldcount = tvShows.size();
                    tvShows.addAll(mostPopularTVShowsResponse.getTvShows());
                    tvShowsAdapter.notifyDataSetChanged();
                    tvShowsAdapter.notifyItemRangeChanged(oldcount,tvShows.size());
                }
            }
                }
//                Toast.makeText(getApplicationContext(),"Totol pages"+ mostPopularTVShowsResponse.getTotalpages(),
//                        Toast.LENGTH_SHORT).show()
        );
    }
    private void toogleLoading(){
        if (currentPage ==1) {
            if (activityMainBinding.getIsLoading() != null && activityMainBinding.getIsLoading()) {
                activityMainBinding.setIsLoading(false);
            } else {
                activityMainBinding.setIsLoading(true);
            }
        }else {
                if(activityMainBinding.getIsLoadingMore() !=null && activityMainBinding.getIsLoadingMore()){
                    activityMainBinding.setIsLoadingMore(false);
                }else {
                    activityMainBinding.setIsLoadingMore(true);
                }
            }
        }

}