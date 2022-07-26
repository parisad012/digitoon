package com.digitoon.batman.models;

import com.digitoon.batman.room.movie.MovieRoom;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RequestOutput {
    @SerializedName("Search")
    private List<MovieRoom> Search;//Movie
    @SerializedName("totalResults")
    private String totalResults;
    @SerializedName("Response")
    private String Response;

    public List<MovieRoom> getSearch() {
        return Search;
    }

    public void setSearch(List<MovieRoom> search) {
        Search = search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }
}
