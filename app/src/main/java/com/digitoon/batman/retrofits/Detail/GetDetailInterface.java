package com.digitoon.batman.retrofits.Detail;

import com.digitoon.batman.helper.Constant;
import com.digitoon.batman.room.detail.DetailRoom;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDetailInterface {
    @GET("/")
    Call<DetailRoom> getDetail(@Query("apikey") String apikey,@Query("i") String id);
}
