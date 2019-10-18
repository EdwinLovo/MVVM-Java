package com.example.mvvm.adapters;

import android.widget.Filter;

import com.example.mvvm.database.entities.Movie;

import java.util.ArrayList;
import java.util.List;

public class CustomFilter extends Filter {

    MovieAdapter movieAdapter;
    List<Movie> filterList;

    public CustomFilter(MovieAdapter movieAdapter, List<Movie> filterList) {
        this.movieAdapter = movieAdapter;
        this.filterList = filterList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults filterResults = new FilterResults();
        if (constraint != null && constraint.length()>0){
            constraint = constraint.toString().toUpperCase();
            List<Movie> filteredMovies = new ArrayList<>();
            for (int i=0; i<filterList.size(); i++){
                if (filterList.get(i).getTitle().toUpperCase().contains(constraint)){
                    filteredMovies.add(filterList.get(i));
                }
            }
            filterResults.count = filteredMovies.size();
            filterResults.values = filteredMovies;
        } else {
            filterResults.count = filterList.size();
            filterResults.values = filterList;
        }

        return filterResults;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        movieAdapter.setMovies((List<Movie>) results.values);
        movieAdapter.notifyDataSetChanged();
    }
}
