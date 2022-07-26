package com.digitoon.batman.retrofits;

import com.digitoon.batman.models.RequestOutput;
import com.digitoon.batman.retrofits.Detail.GetDetailInterface;
import com.digitoon.batman.retrofits.Detail.GetDetailResponseInterface;
import com.digitoon.batman.retrofits.Movie.GetMovieInterface;
import com.digitoon.batman.retrofits.Movie.GetMovieResponseInterface;
import com.digitoon.batman.room.detail.DetailRoom;

import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitApis {
    public RetrofitApis() {
    }

    //----------------------------------------------------------------------------------------------
    public void getMovie(GetMovieResponseInterface mInterface) throws ExecutionException, InterruptedException {

        try {
            GetMovieInterface getPlacesInterface = APIClient.getClient().create(GetMovieInterface.class);

            Call<RequestOutput> call1 = getPlacesInterface.getMovie();

            call1.enqueue(new Callback<RequestOutput>() {
                @Override
                public void onResponse(Call<RequestOutput> call, Response<RequestOutput> response) {
                    try {
                        String s = response.toString();
                        if (response != null && response.code() == 200) {
                            RequestOutput requestOutput = response.body();
                            mInterface.updateUI(true, "", requestOutput);

                        } else {
                            mInterface.updateUI(false, "داده ای دریافت نشد", null);
                        }
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<RequestOutput> call, Throwable t) {
                    try {
                        mInterface.updateUI(false, "failure : " + t.getMessage(), null);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }catch (Exception ex){
            mInterface.updateUI(false,"getPlaces + RetrofitApis",null);
        }
    }
    //----------------------------------------------------------------------------------------------
    public void getDetail(GetDetailResponseInterface mInterface, String imdbID){
        GetDetailInterface getDetailInterface = APIClient.getClient().create(GetDetailInterface.class);
        String apikey="3e974fca";
        Call<DetailRoom> call1=getDetailInterface.getDetail(apikey,imdbID) ;

        call1.enqueue(new Callback<DetailRoom>() {
            @Override
            public void onResponse(Call<DetailRoom> call, Response<DetailRoom> response) {
                try {
                    String s = response.toString();
                    if (response != null && response.code() == 200) {
                        DetailRoom requestOutput = response.body();
                        mInterface.updateUI(true, "", requestOutput);

                    } else {
                        mInterface.updateUI(false, "داده ای دریافت نشد", null);
                    }
                }
                catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<DetailRoom> call, Throwable t) {
                try {
                    mInterface.updateUI(false,"failure : "+t.getMessage(),null);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //----------------------------------------------------------------------------------------------


}
