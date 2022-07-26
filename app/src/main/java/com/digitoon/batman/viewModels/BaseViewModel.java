package com.digitoon.batman.viewModels;

import android.content.Context;
import android.location.Location;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.digitoon.batman.helper.Constant;
import com.digitoon.batman.helper.Utilities;

import java.util.Date;
import java.util.concurrent.ExecutionException;

public class BaseViewModel extends ViewModel {
    public MutableLiveData<Boolean> isOnline=new MutableLiveData<>();
    public MutableLiveData<Boolean> isShowLoading=new MutableLiveData<>();


    public void init(Context context){
    }
    //check for internet connection
    public void check_net(Context context){
        Utilities utilities=new Utilities();
        isOnline.setValue(utilities.isOnline(context));
    }
    //set progressbar for showing in page
    public void showLoading(){
        isShowLoading.setValue(true);
    }
    //set progressbar for not showing in page
    public void notShowLoading(){
        isShowLoading.setValue(false);
    }
    //return internet connection status
    public MutableLiveData<Boolean> getIsOnline() {
        return isOnline;
    }




}
