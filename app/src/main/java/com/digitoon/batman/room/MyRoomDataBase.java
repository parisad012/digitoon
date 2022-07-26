package com.digitoon.batman.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.digitoon.batman.room.detail.DetailDao;
import com.digitoon.batman.room.detail.DetailRoom;
import com.digitoon.batman.room.movie.MovieDao;
import com.digitoon.batman.room.movie.MovieRoom;
import com.digitoon.batman.room.rating.Rating;


//----------
@Database(entities = {MovieRoom.class, DetailRoom.class, Rating.class}
         , version = 1)
 public abstract class MyRoomDataBase extends RoomDatabase {
    public abstract MovieDao MovieDao();
    public abstract DetailDao DetailDao();
    private static MyRoomDataBase INSTANCE;

   public static MyRoomDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MyRoomDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MyRoomDataBase.class,
                            "batman_db")
                            .addCallback(sRoomDatabaseCallback)
                            .build();

                }
            }
        }
        return INSTANCE;
    }

    private static Callback sRoomDatabaseCallback =
            new Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new com.digitoon.batman.room.PopulateDbAsync(INSTANCE).execute();
                }
            };
}