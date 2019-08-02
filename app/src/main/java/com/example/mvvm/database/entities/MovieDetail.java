package com.example.mvvm.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.squareup.moshi.Json;

@Entity(tableName = "moviedetail")
public class MovieDetail {

    @NonNull
    @PrimaryKey
    @Json(name = "imdbID")
    String id;

    @Json(name = "Title")
    String title;

    @Json(name = "Type")
    String type;

    @Json(name = "Poster")
    String poster;

    @Json(name = "Year")
    String year;

    @Json(name = "Released")
    String released;

    @Json(name = "Runtime")
    String runtime;

    @Json(name = "Genre")
    String genre;

    @Json(name = "Director")
    String director;

    @Json(name = "Actors")
    String actors;

    @Json(name = "Plot")
    String plot;

    @Json(name = "Language")
    String language;

    @Json(name = "imdbRating")
    String imdbrating;


    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getImdbrating() {
        return imdbrating;
    }

    public void setImdbrating(String imdbrating) {
        this.imdbrating = imdbrating;
    }
}
