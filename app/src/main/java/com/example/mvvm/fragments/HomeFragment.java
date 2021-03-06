package com.example.mvvm.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mvvm.R;
import com.example.mvvm.adapters.MovieAdapter;
import com.example.mvvm.database.entities.Movie;
import com.example.mvvm.viewmodel.MovieViewModel;

import java.util.List;


public class HomeFragment extends Fragment implements MovieDetailsFragment.OnFragmentInteractionListener {

    private static final String TAG = "Hola";
    private OnFragmentInteractionListener mListener;

    public HomeFragment() { }

    MovieViewModel movieViewModel;
    Button search;
    EditText title;
    Context context;
    MovieDetailsFragment movieDetailsFragment;
    MovieAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        Log.d(TAG, "onCreateView: Creacion");
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        context = view.getContext();
        init(view);
        return view;
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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void init(final View view){
        Log.d(TAG, "init: INIT");
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieDetailsFragment = new MovieDetailsFragment();
        adapter = new MovieAdapter(view.getContext()) {
            @Override
            public void setClickListener(MovieViewHolder holder, final String movieTitle) {
                holder.movieLinearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isConnected()){
                            Log.d("hola", "Titulo: "+movieTitle);
                            movieViewModel.retrieveDetailedMovie(movieTitle);
                            getFragmentManager().beginTransaction().replace(R.id.container, movieDetailsFragment).addToBackStack(null).commit();
                        } else {
                            Toast.makeText(view.getContext(), "Sin internet", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        };

        RecyclerView recyclerView = view.findViewById(R.id.recyclerviewMovies);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        movieViewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                adapter.setMovies(movies);
            }
        });

        search = view.findViewById(R.id.search);
        title = view.findViewById(R.id.title);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected()){
                    movieViewModel.retrieveMovies(title.getText().toString());
                } else {
                    Toast.makeText(view.getContext(), "Sin internet", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if ( networkInfo != null && networkInfo.isConnectedOrConnecting()){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);
        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("Hola", "onQueryTextChange: "+newText);
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

}
