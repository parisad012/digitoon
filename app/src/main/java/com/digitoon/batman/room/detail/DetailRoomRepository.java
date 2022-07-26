package com.digitoon.batman.room.detail;

import android.os.AsyncTask;

import com.digitoon.batman.helper.G;
import com.digitoon.batman.room.rating.DetailWithRatings;
import com.digitoon.batman.room.rating.Rating;

import java.util.List;
import java.util.concurrent.ExecutionException;

//--------------------
public class DetailRoomRepository {
    private static DetailRoomRepository instance;
    private static DetailDao myDao;

    public static DetailRoomRepository getInstance(){
        myDao = G.myRoomDataBase.DetailDao();
       // mAll = loginDao.selectAll();
        if (instance==null){
            instance=new DetailRoomRepository();
        }
        return instance;
    }


    public List<DetailRoom> getAll() throws ExecutionException, InterruptedException {
        return new GetAllAsyncTask(myDao).execute().get();
    }
    public List<Rating> selectByDetailID(int detailID) throws ExecutionException, InterruptedException {
        return new GetAllRatingAsyncTask(myDao,detailID).execute().get();
    }

    public DetailRoom getById(String id) throws ExecutionException, InterruptedException {
        return new GetByIdAsyncTask(myDao,id).execute().get();
    }

//    public void insert (DetailRoom resultModel) {
//        new insertAsyncTask(myDao).execute(resultModel);
//    }
    public void deleteAll(){
        new DeleteAllAsyncTask(myDao).execute();
    }
    public void deleteById(String id){
        new DeleteByIdAsyncTask(myDao,id).execute();
    }

    public Integer getCount() throws ExecutionException, InterruptedException {
         return new GetCountAsyncTask(myDao).execute().get();
    }

//    private static class insertAsyncTask extends AsyncTask<DetailRoom, Void, Void> {
//        private DetailDao mAsyncTaskDao;
//        insertAsyncTask(DetailDao dao) {
//            mAsyncTaskDao = dao;
//        }
//        @Override
//        protected Void doInBackground(final DetailRoom ... params) {
//            mAsyncTaskDao.insert( params[0]);
//            return null;
//        }
//    }
    //-----------------------------------------------------
    private static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private DetailDao mAsyncTaskDao;
        DeleteAllAsyncTask(DetailDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }
    //----------------------------------------------------
    private static class DeleteByIdAsyncTask extends AsyncTask<Void, Void, Void> {
        private DetailDao mAsyncTaskDao;
        private String id;
        DeleteByIdAsyncTask(DetailDao dao,String id){
            mAsyncTaskDao = dao;
            this.id=id;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteById(id);
            return null;
        }
    }
    //----------------------------------------------------
    private static class GetCountAsyncTask extends AsyncTask<Void, Void, Integer> {
        private DetailDao mAsyncTaskDao;
        GetCountAsyncTask(DetailDao dao){
            mAsyncTaskDao = dao;
        }
        @Override
        protected Integer doInBackground(Void... voids) {
            return mAsyncTaskDao.getCount();

        }
    }
  //-----------------------------------------------------
  private static class GetAllAsyncTask extends AsyncTask<Void, Void, List<DetailRoom>> {
      private DetailDao mAsyncTaskDao;
      GetAllAsyncTask(DetailDao dao){
          mAsyncTaskDao = dao;
      }
      @Override
      protected List<DetailRoom> doInBackground(Void... voids) {
          return mAsyncTaskDao.selectAll();

      }
  }
  //-------------------------------------------------

    private static class GetAllRatingAsyncTask extends AsyncTask<Void, Void, List<Rating>> {
        private DetailDao mAsyncTaskDao;
        private int detailID;
        GetAllRatingAsyncTask(DetailDao dao,int detailID){
            mAsyncTaskDao = dao;
            this.detailID=detailID;
        }
        @Override
        protected List<Rating> doInBackground(Void... voids) {
            return mAsyncTaskDao.selectByDetailID(detailID);

        }
    }

    //-----------------------------------------------------
    private static class GetByIdAsyncTask extends AsyncTask<Void, Void, DetailRoom> {
        private DetailDao mAsyncTaskDao;
        private String id;
        GetByIdAsyncTask(DetailDao dao,String id){
            mAsyncTaskDao = dao;
            this.id=id;
        }
        @Override
        protected DetailRoom doInBackground(Void... voids) {
            return mAsyncTaskDao.selectByImdbID( id);

        }
    }
    //-----------------------------------------------------
    public void insert(DetailWithRatings detailWithRatings) {
        new insertAsync(myDao).execute(detailWithRatings);
    }
    private static class insertAsync extends AsyncTask<DetailWithRatings, Void, Void> {
        private DetailDao detailDaoAsync;

        insertAsync(DetailDao detailDao) {
            detailDaoAsync = detailDao;
        }

        @Override
        protected Void doInBackground(DetailWithRatings... detailWithStudents) {

            long identifier=detailDaoAsync.insertDetail(detailWithStudents[0].detail);

            for (Rating rating : detailWithStudents[0].ratings) {
                rating.setId_fk(identifier);
            }
            detailDaoAsync.insertRatings(detailWithStudents[0].ratings);
            return null;
        }
    }
}
