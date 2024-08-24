package com.example.moviesapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.R;
import com.example.moviesapp.databinding.MovieListItemBinding;
import com.example.moviesapp.model.Movie;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    Context context;
    ArrayList<Movie> movieArrayList;

    public MovieAdapter(Context context, ArrayList<Movie> movieArrayList) {
        this.context = context;
        this.movieArrayList = movieArrayList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.movie_list_item,
                parent,
                false);
        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieArrayList.get(position);
        holder.listItemBinding.setMovie(movie);
    }

    @Override
    public int getItemCount() {
        if (movieArrayList!=null){
            return movieArrayList.size();
        }
        return 0;
    }


    class MovieViewHolder extends RecyclerView.ViewHolder{
        private MovieListItemBinding listItemBinding;

        public MovieViewHolder(MovieListItemBinding listItemBinding) {
            super(listItemBinding.getRoot());
            this.listItemBinding = listItemBinding;

            listItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
