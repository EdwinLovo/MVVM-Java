package com.example.mvvm.database;

import android.content.Context;


import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import com.example.mvvm.database.dao.MovieDAO;
import com.example.mvvm.database.dao.MovieDetailDAO;
import com.example.mvvm.database.entities.Movie;
import com.example.mvvm.database.entities.MovieDetail;


@Database(entities = {MovieDetail.class, Movie.class}, version = 3, exportSchema = false)
public abstract class MovieRoomDB extends RoomDatabase {

    private static MovieRoomDB instance;
    private static final String DB_NAME = "moviedb";

    public abstract MovieDAO movieDao();
    public abstract MovieDetailDAO movieDetailDao();

    public static synchronized MovieRoomDB getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), MovieRoomDB.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

}
