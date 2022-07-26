package com.digitoon.batman.retrofits.Movie;


import com.digitoon.batman.helper.Constant;
import com.digitoon.batman.models.RequestOutput;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetMovieInterface {
 //   @Headers("Content-Type: application/json;charset=UTF-8")
    @GET(Constant.URL_GET_MOVIE)

    Call<RequestOutput> getMovie();
}
