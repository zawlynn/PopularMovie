package com.zawlynn.udacity.popularmovie.ui.detail.viewmodel;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zawlynn.udacity.popularmovie.R;
import com.zawlynn.udacity.popularmovie.constants.Columns;
import com.zawlynn.udacity.popularmovie.constants.Constants;
import com.zawlynn.udacity.popularmovie.data.database.MovieDatabase;
import com.zawlynn.udacity.popularmovie.data.database.dao.MovieDao;
import com.zawlynn.udacity.popularmovie.data.database.entity.Movie;
import com.zawlynn.udacity.popularmovie.data.network.NetworkUtils;
import com.zawlynn.udacity.popularmovie.model.Review;
import com.zawlynn.udacity.popularmovie.model.Trailer;


import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.List;


public class ActivityDetailViewmodel extends AndroidViewModel {
    private static final String TAG = "ActivityDetailViewmodel";
    private MutableLiveData<String> _error = new MutableLiveData<>();
    private MutableLiveData<List<Review>> _reviews = new MutableLiveData<>();
    private MutableLiveData<List<Trailer>> _trailers = new MutableLiveData<>();
    private MovieDao movieDao;

    public ActivityDetailViewmodel(@NonNull Application application) {
        super(application);
        movieDao = MovieDatabase.getInstance(application).movieDao();
    }

    public void setFavourte(Movie movie) {
        new PopulateDbAsync(movieDao).execute(movie);
    }

    public LiveData<Movie> checkFavourite(long id) {
        return movieDao.selectFavouriteMovieById(id);
    }

    public void getVideoLists(Context context, long id) {
        RequestQueue queue = NetworkUtils.getInstance(context).getRequestQueue();
        String url = String.format(Constants.VIDEO_TEASER + "?api_key=%s", id, Constants.API_KEY);
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, response -> {
            Type typeOfObjectsList = new TypeToken<List<Trailer>>() {
            }.getType();
            try {
                _trailers.postValue(new Gson()
                        .fromJson(response.getJSONArray(Columns.results).toString(),
                                typeOfObjectsList));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, errorListener) {
            @Override
            public Priority getPriority() {
                return Priority.LOW;
            }
        };
        queue.add(stringRequest).setTag(this);
    }

    public void getReviewLists(Context context, long id) {
        RequestQueue queue = NetworkUtils.getInstance(context).getRequestQueue();
        String url = String.format(Constants.REVIEW + "?api_key=%s", id, Constants.API_KEY);
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, response -> {
            Type typeOfObjectsList = new TypeToken<List<Review>>() {
            }.getType();
            try {
                _reviews.postValue(new Gson()
                        .fromJson(response.getJSONArray(Columns.results).toString(),
                                typeOfObjectsList));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, errorListener) {
            @Override
            public Priority getPriority() {
                return Priority.LOW;
            }
        };
        queue.add(stringRequest).setTag(this);
    }

    private Response.ErrorListener errorListener = error -> {
        if (error instanceof NetworkError) {
            _error.postValue(getApplication().getString(R.string.no_internet));
        } else {
            _error.postValue(getApplication().getString(R.string.no_internet));
        }
    };


    public MutableLiveData<List<Trailer>> get_trailers() {
        return _trailers;
    }

    public MutableLiveData<List<Review>> get_reviews() {
        return _reviews;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        NetworkUtils.getInstance(getApplication()).getRequestQueue().cancelAll(this);
    }

    private static class PopulateDbAsync extends AsyncTask<Movie, Void, Void> {
        private final MovieDao movieDao;
        PopulateDbAsync(MovieDao movieDao) {
            this.movieDao = movieDao;
        }

        @Override
        protected Void doInBackground(Movie... favouriteMovies) {
            if(favouriteMovies[0].isFavourite()){
                movieDao.insertFavourite(favouriteMovies[0]);
            }else {
                movieDao.deleteFavourite(favouriteMovies[0].getId());
            }
            return null;
        }
    }
}
