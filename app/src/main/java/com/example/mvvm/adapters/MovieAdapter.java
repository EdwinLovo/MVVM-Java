package com.example.mvvm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.mvvm.R;
import com.example.mvvm.database.entities.Movie;

import java.util.ArrayList;
import java.util.List;

public abstract class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> implements Filterable {

    private Context context;
    private List<Movie> movies, filterList;
    private CustomFilter filter;

    public MovieAdapter(Context context) {
        this.context = context;
        movies = new ArrayList<>();
        filterList = new ArrayList<>();
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        this.filterList = movies;
        notifyDataSetChanged();
    }

    public abstract void setClickListener(MovieViewHolder holder, String movieTitle);

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_info, parent, false);
        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie currentMovie = movies.get(position);
        holder.title.setText(currentMovie.getTitle());
        holder.type.setText(currentMovie.getType());
        holder.year.setText(currentMovie.getYear());

        Glide.with(context)
                .load(currentMovie.getPoster())
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.poster);

        setClickListener(holder, currentMovie.getTitle());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public Filter getFilter() {
        if (filter==null){
            filter = new CustomFilter(this, filterList);
        }
        return filter;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView year;
        private TextView type;
        private ImageView poster;
        public LinearLayout movieLinearLayout;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.movieTitle);
            year = itemView.findViewById(R.id.movieYear);
            type = itemView.findViewById(R.id.movieType);
            poster = itemView.findViewById(R.id.moviePoster);
            movieLinearLayout = itemView.findViewById(R.id.movieLinearLayout);
        }
    }

}
