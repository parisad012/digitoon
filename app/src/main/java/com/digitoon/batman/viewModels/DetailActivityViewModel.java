package com.digitoon.batman.viewModels;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.digitoon.batman.repositories.DetailRepository;
import com.digitoon.batman.retrofits.Detail.GetDetailResponseInterface;
import com.digitoon.batman.room.detail.DetailRoom;
import com.digitoon.batman.room.detail.DetailRoomRepository;
import com.digitoon.batman.room.movie.MovieRoom;
import com.digitoon.batman.room.movie.MovieRoomRepository;
import com.digitoon.batman.room.rating.DetailWithRatings;
import com.digitoon.batman.room.rating.Rating;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class DetailActivityViewModel extends BaseViewModel {
    public MutableLiveData<String> userMessage=new MutableLiveData<>();
    public MutableLiveData<DetailRoom> detailModel=new MutableLiveData<>();
    DetailRepository detailRepository;
    DetailRoomRepository detailRoomRepository;
    MovieRoomRepository movieRoomRepository;
    private String imdbID;
    public void setImdbID(String id){
        this.imdbID=id;
    }

    @Override
    public void init(Context context) {
        super.init(context);
        isShowLoading.setValue(false);
        detailRepository=DetailRepository.getInstance();
        detailRepository=DetailRepository.getInstance();
        detailRoomRepository=DetailRoomRepository.getInstance();
        movieRoomRepository=MovieRoomRepository.getInstance();
        getDetail(context);
    }
    public LiveData<Boolean> getIsShowLoading(){return isShowLoading;}
    public void getDetail(Context context){
        try{
            if (isOnline.getValue()){//online(context)
                    showLoading();
                    getDataFromServer(context);

            }else{
                userMessage.setValue("no internet");
                getDataFromDB(imdbID);
            }

        }catch (Exception ex){
            String str=ex.getMessage();
        }

    }

    public void getDataFromServer(Context context) throws ExecutionException, InterruptedException {
        try {
            detailRepository.getDetail(getDetailResponseInterface, imdbID);
        }catch (Exception ex){
            String str=ex.getMessage();
        }
    }

    public void getDataFromDB(String id) throws ExecutionException, InterruptedException {
         DetailRoom detailRoom=dao_getById(id);
         if (detailRoom!=null) {
             showData(detailRoom);
         }
    }

    //show data in ui
    public void showData(DetailRoom model){
        if (model!=null) {
            detailModel.setValue(model);
        }else{
            userMessage.setValue("no data find");
        }
    }

     private DetailRoom dao_getById(String id) throws ExecutionException, InterruptedException {
        DetailRoom detailRoom=detailRoomRepository.getById(id);
        detailRoom.setRatings(dao_getRatingByDetaiID(detailRoom.getDetailId()));
        return detailRoom;
    }
    private List<Rating> dao_getRatingByDetaiID(int detailID)throws ExecutionException, InterruptedException{
        return detailRoomRepository.selectByDetailID(detailID);
    }

    //check for internet
    private Boolean online(Context context){
        check_net(context);
        return getIsOnline().getValue();
      //  return false;
    }

    private void dao_deleteById(String imdbId){
        detailRoomRepository.deleteById(imdbId);
    }
    private void dao_insertDetail(DetailRoom room){
        try {
            DetailWithRatings detailWithRatings=new DetailWithRatings(room,room.getRatings());
            detailRoomRepository.insert(detailWithRatings);
        }catch(Exception ex){
            String str=ex.getMessage();
        }
    }
    public LiveData<DetailRoom> getDetailModel(){return detailModel;}
    public LiveData<String> getUserMessage(){
        return userMessage;
    }

    GetDetailResponseInterface getDetailResponseInterface=new GetDetailResponseInterface() {
        @Override
        public void updateUI(boolean response, String str, DetailRoom detail) throws ExecutionException, InterruptedException {
            notShowLoading();
            if (response && detail!=null){
                if (detail!=null) {
                    dao_deleteById(detail.getImdbID());
                    dao_insertDetail(detail);
                    showData(detail);
                }

            }else{
                userMessage.setValue("no data from server");
                getDataFromDB(imdbID);
            }
        }
    };
}
