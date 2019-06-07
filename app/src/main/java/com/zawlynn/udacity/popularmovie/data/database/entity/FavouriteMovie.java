package com.zawlynn.udacity.popularmovie.data.database.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_favourite")
public class FavouriteMovie {
    @ColumnInfo(name = "id")
    @PrimaryKey
    private long id;
    @ColumnInfo(name = "favourite")
    private boolean favourite;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }
}
