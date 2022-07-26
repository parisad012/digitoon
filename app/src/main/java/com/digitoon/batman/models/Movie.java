package com.digitoon.batman.models;

import com.google.gson.annotations.SerializedName;

public class Movie {
    @SerializedName("Title")
    private String Title;
    @SerializedName("Year")
    private String Year;
    @SerializedName("imdbID")
    private String imdbID;
    @SerializedName("Type")
    private String Type;
    @SerializedName("Poster")
    private String Poster;
}
