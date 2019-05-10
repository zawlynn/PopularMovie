package com.zawlynn.udacity.popularmovie.ui.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.zawlynn.udacity.popularmovie.R;
import com.zawlynn.udacity.popularmovie.constants.Constants;
import com.zawlynn.udacity.popularmovie.model.Movie;
import com.zawlynn.udacity.popularmovie.network.NetworkUtils;
import com.zawlynn.udacity.popularmovie.ui.detail.DetailMovieActivity;
import com.zawlynn.udacity.popularmovie.ui.main.adapter.PopularMovieAdapter;
import com.zawlynn.udacity.popularmovie.utils.JSONUtils;
import com.zawlynn.udacity.popularmovie.utils.OnItemClick;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemClick {
    RecyclerView recMovies;
    PopularMovieAdapter adapter;
    List<Movie> movies=new ArrayList<>();
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initUI();
    }
    private void initUI(){
        adapter=new PopularMovieAdapter(this);
        GridLayoutManager layoutManager=new GridLayoutManager(getApplicationContext(),2);
        recMovies=findViewById(R.id.recMovies);
        recMovies.setLayoutManager(layoutManager);
        recMovies.setAdapter(adapter);
        getPopularMovies();
    }
    private void getPopularMovies(){
        new FetchWeatherTask().execute(Constants.API_KEY);
    }

    @SuppressLint("StaticFieldLeak")
    public class FetchWeatherTask extends AsyncTask<String, Void, ArrayList<Movie>> {

        @Override
        protected ArrayList<Movie> doInBackground(String... params) {
            if (params.length == 0) {
                return null;
            }
            String api_key = params[0];
            URL weatherRequestUrl = NetworkUtils.getInstance().buildPopularMovieUrl(api_key);
            try {
                String response = NetworkUtils.getInstance().requestFormAPI(weatherRequestUrl);
                return JSONUtils.getInstance().parseMovies(response);

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> temp) {
            if (movies != null) {
                movies.addAll(temp);
                adapter.submitList(movies);
            }
        }
    }
    public void sortByRate(){
        Collections.sort(movies, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return  -1 * Double.compare(o1.getVote_average(),o2.getVote_average());
            }
        });

        adapter.submitList(movies);
        adapter.notifyDataSetChanged();
    }
    public void sortByPopular(){
        Collections.sort(movies, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return  -1 * Double.compare(o1.getPopularity(),o2.getPopularity());
            }
        });

        adapter.submitList(movies);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(Movie movie) {
        Intent i =new Intent(MainActivity.this, DetailMovieActivity.class);
        i.putExtra(Constants.DATA,movie);
        startActivity(i);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_popular) {
           sortByPopular();

        }else {
            sortByRate();
        }
        return super.onOptionsItemSelected(item);
    }
}
