package com.example.mvvm.services;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RetrofitInstance {

    private static MoviesService moviesService;
    private static final  String BASE_URL = "https://www.omdbapi.com/";
    //String TOKEN_API = "55b04979";

    public static MoviesService getRetrofitInstance(){
        if (moviesService == null){
            moviesService = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()
                    .create(MoviesService.class);
        }
        return moviesService;
    }
}
