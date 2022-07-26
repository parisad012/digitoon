package com.digitoon.batman.repositories;


import com.digitoon.batman.retrofits.Movie.GetMovieResponseInterface;
import com.digitoon.batman.retrofits.RetrofitApis;

public class MovieRepository {
    private static MovieRepository instance;
    private static RetrofitApis retrofitApis;
    public static MovieRepository getInstance(){
        if (instance==null){
            instance=new MovieRepository();
            retrofitApis=new RetrofitApis();
        }
        return instance;
    }
   // ----------------------------------------------------------------------------------------------
    public void getMovie(GetMovieResponseInterface response){
        try {
            retrofitApis.getMovie(response);
        }catch(Exception ex){

        }
    }
    //----------------------------------------------------------------------------------------------

}
