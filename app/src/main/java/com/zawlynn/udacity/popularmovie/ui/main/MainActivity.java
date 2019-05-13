package com.zawlynn.udacity.popularmovie.ui.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.zawlynn.udacity.popularmovie.R;
import com.zawlynn.udacity.popularmovie.constants.Constants;
import com.zawlynn.udacity.popularmovie.model.Movie;
import com.zawlynn.udacity.popularmovie.network.NetworkUtils;
import com.zawlynn.udacity.popularmovie.ui.detail.DetailMovieActivity;
import com.zawlynn.udacity.popularmovie.ui.main.adapter.PopularMovieAdapter;
import com.zawlynn.udacity.popularmovie.utils.JSONUtils;
import com.zawlynn.udacity.popularmovie.utils.OnItemClick;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemClick {
    RecyclerView recMovies;
    PopularMovieAdapter adapter;
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
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recMovies=findViewById(R.id.recMovies);
        recMovies.setLayoutManager(layoutManager);
        recMovies.setAdapter(adapter);
        getPopularMovies();
    }
    private void getPopularMovies() {
        RequestQueue queue = NetworkUtils.getInstance(getApplicationContext()).getRequestQueue();
        String uri = String.format(Constants.POPULAR + "?api_key=%1$s", Constants.API_KEY);
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, uri,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                VolleyLog.d(response.toString());
                List<Movie> movies = JSONUtils.getInstance().parseMovies(response);
                if (movies != null) {
                    adapter.submitList(movies);
                }
            }
        }, errorListener) {
            @Override
            public Priority getPriority() {
                return Priority.LOW;
            }
        };
        queue.add(stringRequest);
    }
    public void getTopRated(){
        RequestQueue queue = NetworkUtils.getInstance(getApplicationContext()).getRequestQueue();
        String uri = String.format(Constants.TOP_RATED + "?api_key=%1$s", Constants.API_KEY);
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, uri,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                ArrayList<Movie> movies = JSONUtils.getInstance().parseMovies(response);
                if (movies != null) {
                    adapter.submitList(movies);
                }
            }
        }, errorListener) {
            @Override
            public Priority getPriority() {
                return Priority.LOW;
            }
        };
        queue.add(stringRequest);
    }
    Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            if (error instanceof NetworkError) {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.no_internet), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    public void onClick(Movie movie) {
        Intent i =new Intent(MainActivity.this, DetailMovieActivity.class);
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
            getPopularMovies();
        }else {
            getTopRated();
        }
        return super.onOptionsItemSelected(item);
    }
}
