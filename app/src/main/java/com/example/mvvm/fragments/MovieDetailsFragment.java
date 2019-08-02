package com.example.mvvm.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.mvvm.R;
import com.example.mvvm.database.entities.MovieDetail;
import com.example.mvvm.viewmodel.MovieViewModel;


public class MovieDetailsFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    TextView title, actors, plot, genre, released, runtime, type, year;
    ImageView poster;
    MovieViewModel movieViewModel;

    public MovieDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_details, container, false);
        initComponents(view);
        init(view);
        return  view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void init(final View view){
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        LiveData<MovieDetail> movie = movieViewModel.getDetailedMovie();
        movieViewModel.getDetailedMovie().observe(this, new Observer<MovieDetail>() {
            @Override
            public void onChanged(MovieDetail movieDetail) {
                Log.d("hola", "Movie: "+movieDetail);
                try {
                    title.setText(movieDetail.getTitle());
                    actors.setText(movieDetail.getActors());
                    plot.setText(movieDetail.getPlot());
                    genre.setText(movieDetail.getGenre());
                    released.setText(movieDetail.getReleased());
                    runtime.setText(movieDetail.getRuntime());
                    type.setText(movieDetail.getType());
                    year.setText(movieDetail.getYear());
                    Glide.with(view.getContext())
                            .load(movieDetail.getPoster())
                            .centerCrop()
                            .crossFade()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(poster);
                } catch (Exception e){
                    Log.d("hola", e.getMessage());
                }
            }
        });
    }

    public void initComponents(View view){
        title = view.findViewById(R.id.movieTitle);
        actors = view.findViewById(R.id.movieActors);
        plot = view.findViewById(R.id.moviePlot);
        genre = view.findViewById(R.id.movieGenre);
        released = view.findViewById(R.id.movieReleased);
        runtime = view.findViewById(R.id.movieRuntime);
        type = view.findViewById(R.id.movieType);
        year = view.findViewById(R.id.movieYear);
        poster = view.findViewById(R.id.moviePoster);
    }

}
