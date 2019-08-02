package com.example.mvvm.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mvvm.database.MovieRoomDB;
import com.example.mvvm.database.dao.MovieDAO;
import com.example.mvvm.database.dao.MovieDetailDAO;
import com.example.mvvm.database.entities.Movie;
import com.example.mvvm.database.entities.MovieDetail;
import com.example.mvvm.repository.MovieRepo;
import com.example.mvvm.services.Coincidences;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieViewModel extends AndroidViewModel {

    private MovieRepo repository;
    LiveData<List<Movie>> allMovies;
    LiveData<MovieDetail> detailedMovie;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        MovieDAO movieDAO = MovieRoomDB.getInstance(application).movieDao();
        MovieDetailDAO movieDetailDAO = MovieRoomDB.getInstance(application).movieDetailDao();
        repository = new MovieRepo(movieDAO, movieDetailDAO);
        allMovies = repository.getAllMovies();
        detailedMovie = repository.getMovieDetail();
    }

    public void insertMovie(Movie movie){
        repository.insertMovie(movie);
    }

    public void insertDetailedMovie(MovieDetail movieDetail){
        repository.insertDetailedMovie(movieDetail);
    }

    public void nukeMovies(){
        repository.nukeMovies();
    }

    public void nukeDetailedMovie(){
        repository.nukeDetailedMovie();
    }

    public void retrieveMovies(String movie){
        this.nukeMovies();
        Call<Coincidences> call = repository.retrieveMovies(movie);
        call.enqueue(new Callback<Coincidences>() {
            @Override
            public void onResponse(Call<Coincidences> call, Response<Coincidences> response) {
                if (!(response.body().getSearch().isEmpty())){
                    for (Movie movie:response.body().getSearch()) {
                        MovieViewModel.this.insertMovie(movie);
                    }
                } else {
                    Log.d("hola", "No se pudo agregar las peliculas");
                }
            }

            @Override
            public void onFailure(Call<Coincidences> call, Throwable t) {
                Log.d("hola", "Error no se pudo agregar las peliculas");
            }
        });
    }

    public void retrieveDetailedMovie(String movie){
        this.nukeDetailedMovie();
        Call<MovieDetail> call = repository.retrieveDetailedMovie(movie);
        call.enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                if (response.isSuccessful()){
                    MovieViewModel.this.insertDetailedMovie(response.body());
                } else {
                    Log.d("hola", "No se pudo agregar los detalles de la pelicula");
                }
            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {
                Log.d("hola", "Error no se pudo agregar los detalles de la pelicula");

            }
        });
    }

    public LiveData<List<Movie>> getAllMovies() {
        return allMovies;
    }

    public void setAllMovies(LiveData<List<Movie>> allMovies) {
        this.allMovies = allMovies;
    }

    public LiveData<MovieDetail> getDetailedMovie() {
        return detailedMovie;
    }

    public void setDetailedMovie(LiveData<MovieDetail> detailedMovie) {
        this.detailedMovie = detailedMovie;
    }
}
