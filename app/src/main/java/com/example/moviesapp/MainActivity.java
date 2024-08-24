package com.example.moviesapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.moviesapp.databinding.ActivityMainBinding;
import com.example.moviesapp.model.Movie;
import com.example.moviesapp.view.MovieAdapter;
import com.example.moviesapp.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //Data Source
    private ArrayList<Movie> movieArrayList;
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;

    //Adapter
    private MovieAdapter adapter;
    private MainActivityViewModel viewModel;

    //Binding
    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Data Binding
        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        //View Model
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        //Displaying movie Live Data
        getPopularMovies();

        //Swipe to refresh layout
        refreshLayout = mainBinding.main;
        refreshLayout.setColorSchemeResources(R.color.black);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPopularMovies();
            }
        });

    }
    private void getPopularMovies(){
        viewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> moviesFromLiveData) {
                movieArrayList =(ArrayList<Movie>) moviesFromLiveData;
                displayMoviesInRecyclerView();
            }
        });
    }

    private void displayMoviesInRecyclerView() {
        recyclerView = mainBinding.recyclerView;
        adapter = new MovieAdapter(this,movieArrayList);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        adapter.notifyDataSetChanged();
    }
}