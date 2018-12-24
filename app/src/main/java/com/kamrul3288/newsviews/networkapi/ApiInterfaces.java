package com.kamrul3288.newsviews.networkapi;

import com.kamrul3288.newsviews.model.NewsList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterfaces {

    @GET("v2/everything")
    Call<NewsList> getNews(@Query("q")String bitCoin, @Query("from")String fromDate, @Query("sortBy")String sortBy,@Query("apiKey")String apiKey);
}
