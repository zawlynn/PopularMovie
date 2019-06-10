package com.zawlynn.udacity.popularmovie.ui.main;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.zawlynn.udacity.popularmovie.R;
import com.zawlynn.udacity.popularmovie.constants.Constants;
import com.zawlynn.udacity.popularmovie.data.database.entity.Movie;
import com.zawlynn.udacity.popularmovie.ui.detail.DetailMovieActivity;
import com.zawlynn.udacity.popularmovie.ui.main.adapter.PopularMovieAdapter;
import com.zawlynn.udacity.popularmovie.ui.main.viewmodel.MainActivityViewmodel;
import com.zawlynn.udacity.popularmovie.utils.OnItemClick;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements OnItemClick {
    @BindView(R.id.recMovies)
    RecyclerView recMovies;
    PopularMovieAdapter adapter;
    private static final String TAG = "MainActivity";
    MainActivityViewmodel viewmodel;
    List<Movie> fav_movies=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initUI();
        viewmodel= ViewModelProviders.of(this).get(MainActivityViewmodel.class);
        viewmodel.getFavMove().observe(this,movies -> {
            if(movies!=null){
                fav_movies.clear();
                fav_movies.addAll(movies);
            }
        });
        viewmodel.get_movies().observe(this, movies -> {
            if(movies!=null){
                adapter.submitList(movies);
                adapter.notifyDataSetChanged();
            }
        });
        viewmodel.get_error().observe(this, s -> {
            if(s!=null){
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void initUI(){
        adapter=new PopularMovieAdapter(this);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        recMovies.setLayoutManager(layoutManager);
        recMovies.setAdapter(adapter);

    }

    @Override
    public void onClick(Movie movie) {
        Intent i =new Intent(MainActivity.this, DetailMovieActivity.class);
        if(fav_movies.contains(movie)){
            movie.setFavourite(true);
        }else {
            movie.setFavourite(false);
        }
        i.putExtra(Constants.DATA,movie);
        startActivity(i);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_popular) {
            viewmodel.getPopularMovies(getApplicationContext());
        }else if (id==R.id.top_rated){
            viewmodel.getTopRated(getApplicationContext());
        }else{
            viewmodel.sortByFav(fav_movies);
        }
        return super.onOptionsItemSelected(item);
    }
}
