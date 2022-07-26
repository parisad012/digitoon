package com.digitoon.batman.viewModels;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.digitoon.batman.helper.Constant;
import com.digitoon.batman.interfaces.NavigatorInterface;
import com.digitoon.batman.models.Movie;
import com.digitoon.batman.models.RequestOutput;
import com.digitoon.batman.repositories.MovieRepository;
import com.digitoon.batman.retrofits.Movie.GetMovieResponseInterface;
import com.digitoon.batman.room.movie.MovieRoom;
import com.digitoon.batman.room.movie.MovieRoomRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivityViewModel extends BaseViewModel {
    public MutableLiveData<String> userMessage=new MutableLiveData<>();
    public MutableLiveData<List<MovieRoom>> movieList=new MutableLiveData<>();
    MovieRepository repository;
    MovieRoomRepository roomRepository;
    private NavigatorInterface navigatorInterface;
    public void setNavigatorInterface(NavigatorInterface navigatorInterface){
        this.navigatorInterface=navigatorInterface;
    }
     //--------------------------------------------------------

    @Override
    public void init(Context context) {
        super.init(context);
        repository=MovieRepository.getInstance();
        roomRepository=MovieRoomRepository.getInstance();
        isShowLoading.setValue(false);
        try {
           check_net(context);
            getMovies(context);
        }catch (Exception ex){
            String str=ex.getMessage();
        }
    }
    public LiveData<Boolean> getIsShowLoading(){return isShowLoading;}
    //---------------------------------------------------------
    private void getMovies(Context context) throws ExecutionException, InterruptedException {
        try{
                if (isOnline.getValue()) {
                    getDataFromServer(context);
                }else{
                    getDataFromDB();
                }

        }catch (Exception ex){
            notShowLoading();
           String str=ex.getMessage();
        }
    }
    //---------------------------------------------------------
    public void getDataFromServer(Context context){
        try {
            if (isOnline.getValue()) {
                showLoading();
                repository.getMovie(getMovieResponseInterface);
                } else {
                userMessage.setValue("no internet");
            }
        }catch (Exception ex){
            notShowLoading();
            userMessage.setValue(ex.getMessage()+"getDataFromServer+mainvm");
        }
    }
    //---------------------------------------------------------
    private void getDataFromDB() throws ExecutionException, InterruptedException {
                 setDataList();
    }

    //---------------------------------------------------------
//    private int dao_getCount() throws ExecutionException, InterruptedException {
//        int i= roomRepository.getCount();
//        return i;
//    }
    //--------------------------------------------------------
    private List<MovieRoom> dao_getAllMovie() throws ExecutionException, InterruptedException {
        return roomRepository.getAll();
    }
    //--------------------------------------------------------
    private void dao_saveList(){
//        Mapper mapper=new Mapper();
//        roomRepository.insert(mapper.mapToEntity_list(movieList.getValue()));
        roomRepository.insert(movieList.getValue());
    }
    //--------------------------------------------------------
    private void dao_deleteAll(){
        roomRepository.deleteAll();
    }
    //--------------------------------------------------------
    public LiveData<String> getUserMessage(){
        return userMessage;
    }
    //--------------------------------------------------------
    public void setDataList() throws ExecutionException, InterruptedException {
//       Mapper mapper=new Mapper();
//        movieList.setValue(mapper.mapToModel_list(dao_getAllMovie()));
        movieList.setValue(dao_getAllMovie());
    }
    //--------------------------------------------------------
    public LiveData<List<MovieRoom>> getMovieList(){
        return movieList;
    }

    //-------------------------------------------------------------------
    public void showDetail(MovieRoom model){//Movie
             //go to detail activity
        navigatorInterface.onItemClick(model);
    }
    //-------------------------------------------------------------------
    GetMovieResponseInterface getMovieResponseInterface=new GetMovieResponseInterface() {
        @Override
        public void updateUI(boolean response, String str, RequestOutput requestOutput)
                throws ExecutionException, InterruptedException {
            notShowLoading();
            //save to database
            if (response && requestOutput!=null) {
                //Movie
                List<MovieRoom> movies=requestOutput.getSearch();//new Mapper().ReqOutToPlaceModel(requestOutput);
                movieList.setValue(movies);
                dao_deleteAll();
                dao_saveList();
                setDataList();
            }else{
                userMessage.setValue("no data from server");
                getDataFromDB();
            }
        }
    };
    //---------------------------------------------------------------
}
