package com.zawlynn.udacity.popularmovie.data.database.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.zawlynn.udacity.popularmovie.data.database.entity.FavouriteMovie;


@Dao
public interface MovieDao {
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavourite(FavouriteMovie entity);
    @Query("SELECT * FROM tbl_favourite  WHERE id = :id")
    LiveData<FavouriteMovie> selectFavouriteMovieById(long id);
}
