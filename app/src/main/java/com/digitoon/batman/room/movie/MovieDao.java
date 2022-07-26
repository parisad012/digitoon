package com.digitoon.batman.room.movie;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.digitoon.batman.helper.Constant;

import java.util.List;

@Dao
public interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<MovieRoom> placeRooms);

    @Query("DELETE FROM "+ Constant.table_movie)
    void deleteAll();

    @Query("SELECT * FROM "+Constant.table_movie)
    List<MovieRoom> selectAll();

    @Query("SELECT COUNT(*) FROM "+Constant.table_movie)
    int getCount();

    @Query("SELECT * FROM "+Constant.table_movie+" WHERE imdbID= :imdbID")
    MovieRoom selectByImdbId(String imdbID);

}
