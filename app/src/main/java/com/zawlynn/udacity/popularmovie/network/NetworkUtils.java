package com.zawlynn.udacity.popularmovie.network;

import android.net.Uri;
import android.util.Log;

import com.zawlynn.udacity.popularmovie.constants.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import static android.support.constraint.Constraints.TAG;

public class NetworkUtils {

    private static NetworkUtils instance;

    public static NetworkUtils getInstance() {
        if (instance == null) {
            instance = new NetworkUtils();
        }
        return instance;
    }

    public URL buildPopularMovieUrl(String apiKey) {
        Uri popular_movies = Uri.parse(Constants.BASE_URL).buildUpon()
                .appendQueryParameter(Constants.K_API_KEY, apiKey).build();
        try {
            return new URL(popular_movies.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public  String requestFormAPI(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            String response = null;
            if (hasInput) {
                response = scanner.next();
            }
            scanner.close();
            return response;
        } finally {
            urlConnection.disconnect();

        }
    }
}
