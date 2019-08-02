package com.example.mvvm.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mvvm.database.entities.MovieDetail;

@Dao
public interface MovieDetailDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MovieDetail movieDetail);

    @Query("select * from moviedetail")
    LiveData<MovieDetail> getDetailedMovie();

    @Query("delete from moviedetail")
    void nukeTable();

}
