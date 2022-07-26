package com.digitoon.batman.repositories;


import com.digitoon.batman.retrofits.Detail.GetDetailResponseInterface;
import com.digitoon.batman.retrofits.RetrofitApis;

public class DetailRepository {
    private static DetailRepository instance;
    private static RetrofitApis retrofitApis;
    public static DetailRepository getInstance(){
        if (instance==null){
            instance=new DetailRepository();
            retrofitApis=new RetrofitApis();
        }
        return instance;
    }
   // ----------------------------------------------------------------------------------------------
    public void getDetail(GetDetailResponseInterface response, String imdbID){
        try {
            retrofitApis.getDetail(response,imdbID);
        }catch(Exception ex){
            String str=ex.getMessage();
        }
    }
    //----------------------------------------------------------------------------------------------

}
