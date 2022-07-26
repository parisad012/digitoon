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
    public MutableLiveData<String> title=new MutableLiveData<>();
    public MutableLiveData<String> year=new MutableLiveData<>();
    public MutableLiveData<String> poster=new MutableLiveData<>();
    DetailRepository detailRepository;
    DetailRoomRepository detailRoomRepository;
    MovieRoomRepository movieRoomRepository;
    private String imdbID;
    public void setImdbID(String id){
        this.imdbID=id;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
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
    public LiveData<String> getPoster(){return poster;}
    public LiveData<Boolean> getIsShowLoading(){return isShowLoading;}
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getDetail(Context context){
        try{
            if (online(context)){
                //if (dao_getById(imdbID)!=null){
                    showLoading();
                    getDataFromServer(context);

              //  }
//                else{
//                    //at first get data from places
//                    PlaceRoom placeRoom=dao_getById_place(venueId);
//                    showData_place(placeRoom);
//
//                }
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
          //  showLoading();
            detailRepository.getDetail(getDetailResponseInterface, imdbID);
        }catch (Exception ex){
            String str=ex.getMessage();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getDataFromDB(String id) throws ExecutionException, InterruptedException {
         DetailRoom detailRoom=dao_getById(id);
         if (detailRoom!=null) {
            // DetailModel model = new Mapper().mapToDetailModel(detailRoom);
             showData(detailRoom);
         }
//         else{
//             MovieRoom movieRoom =dao_getById_place(id);
//             showData_movie(movieRoom);
//         }
    }

    public void showData_movie(MovieRoom model){
        if (model!=null) {
            DetailRoom dm=new DetailRoom();
           // dm=new Mapper().PlaceRoomToDetailModel(model);
          //  detailModel.setValue(dm);
            title.setValue(model.getTitle());
            year.setValue(model.getYear());
            poster.setValue(model.getPoster());

        }else{
            userMessage.setValue("no data find");
        }
    }

    //show data in ui
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void showData(DetailRoom model){
        if (model!=null) {
            detailModel.setValue(model);
//            title.setValue(model.getTitle());
            String s = model.getRatings().stream().map(e->e.getSource()).collect(Collectors.joining(","));
            title.setValue(s);
            poster.setValue(model.getPoster());

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
        Boolean myTest=getIsOnline().getValue();
        return getIsOnline().getValue();
      //  return false;
    }


    private void dao_insertDetail(DetailRoom room){
        try {
            DetailWithRatings detailWithRatings=new DetailWithRatings(room,room.getRatings());
//            detailRoomRepository.insert(room);
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
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void updateUI(boolean response, String str, DetailRoom detail) throws ExecutionException, InterruptedException {
            notShowLoading();
            if (response && detail!=null){
               // DetailRoom model=new Mapper().ReqOutToDetailModel(detailOutput);
                if (detail!=null) {
                    dao_insertDetail(detail);//new Mapper().mapToDetailRoom(model)
                    showData(detail);
                }

            }else{
                userMessage.setValue(str);
            }
        }
    };
}
