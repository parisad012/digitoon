package com.digitoon.batman.helper;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.Settings;

import java.io.IOException;
import java.text.DecimalFormat;

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
