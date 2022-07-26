package com.digitoon.batman.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utilities {
    public Utilities() {
    }

    //check if connection(wifi) exit or not
    public  boolean isOnline(Context context){
        ConnectivityManager mgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = mgr.getActiveNetworkInfo();
        if (netInfo != null) {
            if (netInfo.isConnected()) {
                return true;
                //return checkNet();
            }else {
                //No internet
                return false;
            }
        } else {
            //No internet
            return false;
        }
    }

}

