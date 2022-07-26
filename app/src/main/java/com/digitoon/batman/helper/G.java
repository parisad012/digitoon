package com.digitoon.batman.helper;

import android.app.Application;
import android.content.Context;

import com.digitoon.batman.room.MyRoomDataBase;

public class G extends Application {
  public static Context context;
  public static MyRoomDataBase myRoomDataBase;
  //------------------------------------------------------------------------------------------------
 @Override
 public void onCreate() {
   super.onCreate();
   context=getApplicationContext();
   myRoomDataBase = MyRoomDataBase.getDatabase(getApplicationContext());
 }
  //----------------------------------------------------------------------------

 }
