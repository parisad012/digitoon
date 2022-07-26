package com.digitoon.batman.activities;

import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.digitoon.batman.R;
import com.digitoon.batman.databinding.ActivityDetailBinding;
import com.digitoon.batman.helper.Constant;
import com.digitoon.batman.room.detail.DetailRoom;
import com.digitoon.batman.viewModels.DetailActivityViewModel;
import com.squareup.picasso.Picasso;

import java.util.stream.Collectors;

public class DetailActivity extends AppCompatActivity {
    DetailActivityViewModel viewModel;
    ActivityDetailBinding myBinding;
    String imdbID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(DetailActivityViewModel.class);
        myBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        setObserver();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onResume() {
        super.onResume();
        imdbID= getIntent().getStringExtra(Constant.MOVIE);
       // setHouseChar(house);
        //send selected house to viewmodel
       // viewModel.setHouse(house);
        //start to get founder detail from server with viewmodel
        viewModel.setImdbID(imdbID);
        viewModel.init(getApplicationContext());
    }

    //set observer of DetailActViewModels
    public void setObserver(){
        viewModel.getDetailModel().observe(this, new Observer<DetailRoom>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onChanged(DetailRoom detail) {
               // detailRoom.getRatings().stream().map(e->e.getSource()).collect(Collectors.joining(","))
                myBinding.setTitle(detail.getTitle());
                try {
                    if (!detail.getPoster().isEmpty()) {
                        Picasso.get().load(detail.getPoster()).fit().centerCrop()
                                .placeholder(R.drawable.image_no)
                                .error(R.drawable.image_no)
                                .into(myBinding.poster);

                    }
                }catch(Exception ex){
                    String str=ex.getMessage();
                }
            }
        });
        viewModel.getUserMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (!s.isEmpty())
                    Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }
        });
        viewModel.getIsShowLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                myBinding.setShowLoading(aBoolean);
            }
        });
    }
    //set characters of selected house from main page
//    private void setHouseChar(House house){
//        myBinding.setName(house.getName());
//        myBinding.setRegion(house.getRegion());
//        String title=String.join("-", house.getTitles());
//        myBinding.setTitles(title);
//    }
}