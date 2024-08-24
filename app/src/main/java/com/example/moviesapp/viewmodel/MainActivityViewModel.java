package com.example.moviesapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.moviesapp.model.Movie;
import com.example.moviesapp.model.MovieRepository;
import com.example.moviesapp.model.Result;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    private MovieRepository repository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.repository = new MovieRepository(application);
    }
    //LiveData
    public LiveData<List<Movie>> getAllMovies(){
        return repository.getMutableLiveData();
    }
}
