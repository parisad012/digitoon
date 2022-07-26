package com.digitoon.batman.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.digitoon.batman.R;
import com.digitoon.batman.databinding.LayoutMovieItemBinding;
import com.digitoon.batman.room.movie.MovieRoom;
import com.digitoon.batman.viewModels.MainActivityViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder>
{
    private List<MovieRoom> AdapterList;
    private MainActivityViewModel viewModel;
    public MovieAdapter(MainActivityViewModel vm) {
        this.viewModel=vm;
    }
    public void setDataList(List<MovieRoom> AdapterList){
        this.AdapterList=AdapterList;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutMovieItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.layout_movie_item, parent, false);

        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        MovieRoom model = AdapterList.get(position);
        try {
            Picasso.get().load(model.getPoster()).fit().centerCrop()
                    .placeholder(R.drawable.ic_no_poster)
                    .error(R.drawable.ic_no_poster)
                    .into(holder.binding.poster);
        }catch (Exception ex){

        }

        holder.bind(model,position);

    }


    @Override
    public int getItemCount() {
        if (AdapterList!=null) {
            return AdapterList.size();
        }
        return 0;
    }
    @Override
    public int getItemViewType(int position) { return position; }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public LayoutMovieItemBinding binding;

        public MyViewHolder(LayoutMovieItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }


        public void bind(Object obj, int index) {
            binding.setPosition(index);
            binding.setModel(AdapterList.get(index));
            binding.setViewModel(viewModel);
            binding.setTitle((AdapterList.get(index).getTitle().isEmpty() ? "no title":AdapterList.get(index).getTitle()));
            binding.setYear((AdapterList.get(index).getYear().isEmpty() ? "no Year":AdapterList.get(index).getYear()));
            binding.executePendingBindings();
        }
    }

}



