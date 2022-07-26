package com.digitoon.batman.room.movie;

import android.os.AsyncTask;

import androidx.room.Room;

import com.digitoon.batman.helper.G;

import java.util.List;
import java.util.concurrent.ExecutionException;

//--------------------
public class MovieRoomRepository {
    private static MovieRoomRepository instance;
    private static MovieDao myDao;
    static List<MovieRoom> mAll;

    public static MovieRoomRepository getInstance(){
        myDao = G.myRoomDataBase.MovieDao();
       // mAll = loginDao.selectAll();
        if (instance==null){
            instance=new MovieRoomRepository();
        }
        return instance;
    }


    public List<MovieRoom> getAll() throws ExecutionException, InterruptedException {
        return new GetAllAsyncTask(myDao).execute().get();
    }

    public MovieRoom getById(String id) throws ExecutionException, InterruptedException {
        return new GetByIdAsyncTask(myDao,id).execute().get();
    }

    public void insert (List<MovieRoom> resultModel) {

        new insertAsyncTask(myDao).execute(resultModel);
    }
    public void deleteAll(){
        new DeleteAllAsyncTask(myDao).execute();
    }

    public Integer getCount() throws ExecutionException, InterruptedException {
         return new GetCountAsyncTask(myDao).execute().get();
    }

    private static class insertAsyncTask extends AsyncTask<List<MovieRoom>, Void, Void> {
        private MovieDao mAsyncTaskDao;
        insertAsyncTask(MovieDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final List<MovieRoom>... params) {
            mAsyncTaskDao.insert( params[0]);
            return null;
        }
    }
    //-----------------------------------------------------
    private static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private MovieDao mAsyncTaskDao;
        DeleteAllAsyncTask(MovieDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }
    //----------------------------------------------------
    private static class GetCountAsyncTask extends AsyncTask<Void, Void, Integer> {
        private MovieDao mAsyncTaskDao;
        GetCountAsyncTask(MovieDao dao){
            mAsyncTaskDao = dao;
        }
        @Override
        protected Integer doInBackground(Void... voids) {
            return mAsyncTaskDao.getCount();

        }
    }
  //-----------------------------------------------------
  private static class GetAllAsyncTask extends AsyncTask<Void, Void, List<MovieRoom>> {
      private MovieDao mAsyncTaskDao;
      GetAllAsyncTask(MovieDao dao){
          mAsyncTaskDao = dao;
      }
      @Override
      protected List<MovieRoom> doInBackground(Void... voids) {
          return mAsyncTaskDao.selectAll();

      }
  }
    //-----------------------------------------------------
    private static class GetByIdAsyncTask extends AsyncTask<Void, Void, MovieRoom> {
        private MovieDao mAsyncTaskDao;
        private String id;
        GetByIdAsyncTask(MovieDao dao, String id){
            mAsyncTaskDao = dao;
            this.id=id;
        }
        @Override
        protected MovieRoom doInBackground(Void... voids) {
            return mAsyncTaskDao.selectByImdbId( id);

        }
    }
    //-----------------------------------------------------
}
