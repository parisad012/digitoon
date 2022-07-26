package com.digitoon.batman.room;


import android.os.AsyncTask;

import com.digitoon.batman.room.movie.MovieDao;


//----------------
public class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

    private final MovieDao movieDao;

    PopulateDbAsync(MyRoomDataBase db) {

        movieDao = db.MovieDao();
    }

    @Override
    protected Void doInBackground(final Void... params) {

        return null;
    }
}
