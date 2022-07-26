package com.digitoon.batman.room.movie;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.digitoon.batman.helper.Constant;

import java.io.Serializable;

@Entity(tableName = Constant.table_movie)
public class MovieRoom implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "tableId")
    private int tableId;
    public int getTableId() {
        return tableId;
    }
    public void setTableId(int tableId) {
        this.tableId = tableId;
    }
    //-----------------------

    @ColumnInfo(name = "Title")
    private String Title;
    @ColumnInfo(name = "Year")
    private String Year;
    @ColumnInfo(name = "imdbID")
    private String imdbID;
    @ColumnInfo(name = "Type")
    private String Type;
    @ColumnInfo(name = "Poster")
    private String Poster;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }
}
