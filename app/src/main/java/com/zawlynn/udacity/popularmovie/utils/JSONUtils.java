package com.zawlynn.udacity.popularmovie.utils;

import com.zawlynn.udacity.popularmovie.constants.Columns;
import com.zawlynn.udacity.popularmovie.data.database.MovieDatabase;
import com.zawlynn.udacity.popularmovie.data.database.dao.MovieDao;
import com.zawlynn.udacity.popularmovie.data.database.entity.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONUtils {
    private static JSONUtils instance;
    public static JSONUtils getInstance(){
        if(instance==null){
            instance=new JSONUtils();
        }
        return instance;
    }
    public ArrayList<Movie> parseMovies(JSONObject _json){
        ArrayList<Movie> movies=new ArrayList<>();
        try {
            if (_json.optJSONArray(Columns.results) != null) {
                JSONArray array=_json.getJSONArray(Columns.results);
                for(int i=0;i<array.length();i++) {
                    Movie movie = new Movie();
                    JSONObject _object = array.getJSONObject(i);
                    movie.setVote_count(_object.optInt(Columns.vote_count));
                    movie.setId(_object.optInt(Columns.id));
                    movie.setVideo(_object.optBoolean(Columns.video));
                    movie.setVote_average(_object.optDouble(Columns.vote_average));
                    movie.setTitle(_object.optString(Columns.title));
                    movie.setPopularity(_object.optDouble(Columns.popularity));
                    movie.setPoster_path(_object.optString(Columns.poster_path));
                    movie.setOriginal_language(_object.optString(Columns.original_language));
                    movie.setOriginal_title(_object.optString(Columns.original_title));
                    movie.setBackdrop_path(_object.optString(Columns.backdrop_path));
                    movie.setAdult(_object.optBoolean(Columns.adult));
                    movie.setOverview(_object.optString(Columns.overview));
                    movie.setRelease_date(_object.optString(Columns.release_date));
                    movies.add(movie);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movies;
    }
}
