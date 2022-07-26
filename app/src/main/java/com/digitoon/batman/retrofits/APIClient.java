package com.digitoon.batman.retrofits;


import com.digitoon.batman.helper.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    public static final String BASE_URL = Constant.URL_BASE;
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        try {
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(setTimeout(10))
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
        }catch (Exception ex){
            String str=ex.getMessage();
        }
        return retrofit;
    }
    //set custom timeout for all retrofit api's
    public static OkHttpClient setTimeout(long myTime){
        return new OkHttpClient().newBuilder()
                .connectTimeout(myTime, TimeUnit.SECONDS)
                .readTimeout(myTime, TimeUnit.SECONDS)
                .writeTimeout(myTime, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .build();
    }
    public static void clearRetrofit(){
        retrofit=null;
    }
}

