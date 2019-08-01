package com.example.mvvm.services;

import com.example.mvvm.database.entities.Movie;
import com.squareup.moshi.Json;

import java.util.List;

public class Coincidences {

    @Json(name = "totalResults")
    String totalResults;

    @Json(name = "Search")
    List<Movie> search;

    @Json(name = "Response")
    String response;

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public List<Movie> getSearch() {
        return search;
    }

    public void setSearch(List<Movie> search) {
        this.search = search;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
