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
}
