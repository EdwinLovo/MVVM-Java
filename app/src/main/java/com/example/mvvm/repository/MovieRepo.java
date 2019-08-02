package com.example.mvvm.repository;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;

import com.example.mvvm.database.dao.MovieDAO;
import com.example.mvvm.database.dao.MovieDetailDAO;
import com.example.mvvm.database.entities.Movie;
import com.example.mvvm.database.entities.MovieDetail;
import com.example.mvvm.services.Coincidences;
import com.example.mvvm.services.RetrofitInstance;

import retrofit2.Call;

import java.util.List;

public class MovieRepo {

    private MovieDAO movieDAO;
    private MovieDetailDAO movieDetailDAO;
    LiveData<List<Movie>> allMovies;
    LiveData<MovieDetail> movieDetail;

    public MovieRepo(MovieDAO movieDAO, MovieDetailDAO movieDetailDAO) {
        this.movieDAO = movieDAO;
        this.movieDetailDAO = movieDetailDAO;
        movieDetail = movieDetailDAO.getDetailedMovie();
        allMovies = movieDAO.getAllMovies();
    }

    @WorkerThread
    public void insertMovie(Movie movie){
        movieDAO.insert(movie);
    }

    @WorkerThread
    public void insertDetailedMovie(MovieDetail movieDetail){
        movieDetailDAO.insert(movieDetail);
    }

    @WorkerThread
    public void nukeMovies(){
        movieDAO.nukeTable();
    }

    @WorkerThread
    public void nukeDetailedMovie(){
        movieDetailDAO.nukeTable();
    }

    public Call<Coincidences> retrieveMovies(String movie){
        Call<Coincidences> call = RetrofitInstance.getRetrofitInstance().getMovies(movie, "55b04979");
        return call;
    }

    public Call<MovieDetail> retrieveDetailedMovie(String movie){
        Call<MovieDetail> call = RetrofitInstance.getRetrofitInstance().getDetailedMovie(movie, "55b04979");
        return call;
    }

    public LiveData<List<Movie>> getAllMovies() {
        return allMovies;
    }

    public void setAllMovies(LiveData<List<Movie>> allMovies) {
        this.allMovies = allMovies;
    }

    public LiveData<MovieDetail> getMovieDetail() {
        return movieDetail;
    }

    public void setMovieDetail(LiveData<MovieDetail> movieDetail) {
        this.movieDetail = movieDetail;
    }
}
