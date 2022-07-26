package com.digitoon.batman.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.digitoon.batman.R;
import com.digitoon.batman.adapters.MovieAdapter;
import com.digitoon.batman.databinding.ActivityMainBinding;
import com.digitoon.batman.helper.Constant;
import com.digitoon.batman.interfaces.NavigatorInterface;
import com.digitoon.batman.room.movie.MovieRoom;
import com.digitoon.batman.viewModels.MainActivityViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigatorInterface {

    MainActivityViewModel viewModel;
    ActivityMainBinding myBinding;
    MovieAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        myBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setObserver();
        start();
        initRV();


    }

    public void start(){
        //set navigation for navigate to detail page
        viewModel.setNavigatorInterface(this);
        //start to get house list from server with viewmodel
        viewModel.init(getApplicationContext());
    }
    //set observer of viewmodels
    public void setObserver(){
        viewModel.getUserMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }
        });
        //-----------------
        viewModel.getMovieList().observe(this, new Observer<List<MovieRoom>>() {
            @Override
            public void onChanged(List<MovieRoom> data) {
                //show in rv
                try {
                    myAdapter.setDataList(data);
                }catch(Exception ex){
                    String str=ex.getMessage();
                }
            }
        });
        //-----------------
        viewModel.getIsShowLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                myBinding.setShowLoading(aBoolean);
            }
        });
    }
    //init recyclerview for house list
    public void initRV(){
        LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(myBinding.recyclerview.getContext(), LinearLayoutManager.VERTICAL, false);
        myAdapter=new MovieAdapter(viewModel);
        myBinding.recyclerview.setHasFixedSize(true);
        myBinding.recyclerview.setLayoutManager(linearLayoutManager);
        myBinding.recyclerview.setItemAnimator(new DefaultItemAnimator());
        myBinding.recyclerview.setAdapter(myAdapter);

    }
    //go to detail page and send selected house model to detail
    @Override
    public void onItemClick(MovieRoom model) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra(Constant.MOVIE,model.getImdbID());
        startActivity(intent);
    }
}