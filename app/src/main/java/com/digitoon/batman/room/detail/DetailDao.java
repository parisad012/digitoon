package com.digitoon.batman.room.detail;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;


import com.digitoon.batman.helper.Constant;
import com.digitoon.batman.room.rating.Rating;

import java.util.List;

@Dao
public interface DetailDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insert(DetailRoom detailRoom);
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertDetail(DetailRoom detailRoom);

    @Insert
    void insertRatings(List<Rating> ratings);

    @Query("DELETE FROM "+ Constant.table_detail)
    void deleteAll();

    @Query("SELECT * FROM "+Constant.table_detail)
    List<DetailRoom> selectAll();

    @Query("SELECT COUNT(*) FROM "+Constant.table_detail)
    int getCount();

    @Query("SELECT * FROM "+Constant.table_detail+" WHERE imdbID= :imdbID")
    DetailRoom selectByImdbID(String imdbID);

    @Query("SELECT * FROM "+Constant.table_rating+" WHERE id_fk= :detailID")
    List<Rating> selectByDetailID(int detailID);

}
