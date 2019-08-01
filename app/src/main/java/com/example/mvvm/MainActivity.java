package com.example.mvvm;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import com.example.mvvm.fragments.HomeFragment;
import com.example.mvvm.fragments.MovieDetailsFragment;

public class MainActivity extends AppCompatActivity
        implements HomeFragment.OnFragmentInteractionListener, MovieDetailsFragment.OnFragmentInteractionListener {

    HomeFragment homeFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).addToBackStack(null).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
