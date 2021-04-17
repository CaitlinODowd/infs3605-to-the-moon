package com.infs3605.tothemoon;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsInterface {

    //HTTP GET calls for articles in JSON format
    @GET("top-headlines")
    Call<News> getHeadlines(
            @Query("country") String country ,
            @Query("apiKey") String apiKey
    );

    @GET("everything")
    Call<News> getNews(
            @Query("q") String q,
            @Query("apiKey") String apiKey

    );

}
