package com.infs3605.tothemoon;

/*
INFS3605 Capstone Project T1 2021
To the Moon
Caitlin O'Dowd z5183007
Sharon Cheung z5162825
Neil Matani z5162753
Aiden Mansley z5265120
Connor Williams z5189800
Timothy Baker z5162709
*/

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
