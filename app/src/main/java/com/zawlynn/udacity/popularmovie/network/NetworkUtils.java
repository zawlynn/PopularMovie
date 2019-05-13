package com.zawlynn.udacity.popularmovie.network;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
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

    private Context mContext;
    private RequestQueue mRequestQueue;

    private NetworkUtils(Context context) {
        mContext = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized NetworkUtils getInstance(Context context) {
        if (instance == null) {
            instance = new NetworkUtils(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext);
        }
        return mRequestQueue;
    }

//    public static NetworkUtils getInstance() {
//        if (instance == null) {
//            instance = new NetworkUtils();
//        }
//        return instance;
//    }
//
//    public URL buildPopularMovieUrl(String request_type,String apiKey) {
//        String _url=Constants.BASE_URL+request_type;
//        Uri popular_movies = Uri.parse(_url).buildUpon()
//                .appendQueryParameter(Constants.K_API_KEY, apiKey).build();
//        try {
//            return new URL(popular_movies.toString());
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public  String requestFormAPI(URL url) throws IOException {
//        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//        try {
//            InputStream in = urlConnection.getInputStream();
//
//            Scanner scanner = new Scanner(in);
//            scanner.useDelimiter("\\A");
//
//            boolean hasInput = scanner.hasNext();
//            String response = null;
//            if (hasInput) {
//                response = scanner.next();
//            }
//            scanner.close();
//            return response;
//        } finally {
//            urlConnection.disconnect();
//
//        }
//    }

}
