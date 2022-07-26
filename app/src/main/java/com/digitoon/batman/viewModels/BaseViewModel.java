package com.digitoon.batman.viewModels;

import android.content.Context;
import android.content.IntentFilter;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.digitoon.batman.helper.ConnectionReceiver;
import com.digitoon.batman.helper.Constant;
import com.digitoon.batman.helper.Utilities;

import java.util.Date;
import java.util.concurrent.ExecutionException;

public class BaseViewModel extends ViewModel implements ConnectionReceiver.ReceiverListener {
    public MutableLiveData<Boolean> isOnline=new MutableLiveData<>();
    public MutableLiveData<Boolean> isShowLoading=new MutableLiveData<>();


    public void init(Context context){
        check_net(context);
    }
    //check for internet connection
    public void check_net(Context context){
//        Utilities utilities=new Utilities();
//        isOnline.setValue(utilities.isOnline(context));


            // initialize intent filter
            IntentFilter intentFilter = new IntentFilter();

            // add action
            intentFilter.addAction("android.new.conn.CONNECTIVITY_CHANGE");

            // register receiver
            context.registerReceiver(new ConnectionReceiver(), intentFilter);

            // Initialize listener
            ConnectionReceiver.Listener = this;

            // Initialize connectivity manager
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            // Initialize network info
            NetworkInfo networkInfo = manager.getActiveNetworkInfo();

            // get connection status
            boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();

            // display snack bar
            isOnline.setValue(isConnected);

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


    @Override
    public void onNetworkChange(boolean isConnected) {
        isOnline.setValue(isConnected);
    }
}
