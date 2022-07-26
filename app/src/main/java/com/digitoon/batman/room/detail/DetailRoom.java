package com.digitoon.batman.room.detail;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.digitoon.batman.helper.Constant;
import com.digitoon.batman.room.rating.Rating;

import java.util.List;


@Entity(tableName = Constant.table_detail)
public class DetailRoom {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "detailId")
    private int detailId;

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
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
    private String imdbRating;
    private String Runtime;
    private String Genre;
    private String Director;
    private String Writer;
    private String Actors;
    private String Language;
    private String Country;
    private String Awards;
    private String Plot;
    @Ignore
    private List<Rating> Ratings;

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public String getWriter() {
        return Writer;
    }

    public void setWriter(String writer) {
        Writer = writer;
    }

    public String getActors() {
        return Actors;
    }

    public void setActors(String actors) {
        Actors = actors;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getAwards() {
        return Awards;
    }

    public void setAwards(String awards) {
        Awards = awards;
    }

    public String getPlot() {
        return Plot;
    }

    public void setPlot(String plot) {
        Plot = plot;
    }

    public String getRuntime() {
        return Runtime;
    }

    public void setRuntime(String runtime) {
        Runtime = runtime;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

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

    public List<Rating> getRatings() {
        return Ratings;
    }

    public void setRatings(List<Rating> ratings) {
        Ratings = ratings;
    }
}
