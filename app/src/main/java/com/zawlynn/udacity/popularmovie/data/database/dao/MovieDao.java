package com.zawlynn.udacity.popularmovie.data.database.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.zawlynn.udacity.popularmovie.data.database.entity.Movie;

import java.util.List;


@Dao
public interface MovieDao {
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavourite(Movie entity);
    @Query("DELETE FROM tbl_movie WHERE id==:id")
    void deleteFavourite(long id);
    @Query("SELECT * FROM tbl_movie")
    LiveData<List<Movie>> selectFavMovie();
}
