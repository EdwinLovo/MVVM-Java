package com.example.mvvm.services;


import com.example.mvvm.database.entities.MovieDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesService {

    @GET("/")
    Call<Coincidences> getMovies(@Query("s") String s, @Query("apikey") String apikey);

    @GET("/")
    Call<MovieDetail> getDetailedMovie(@Query("t") String s, @Query("apikey") String apikey);

}
