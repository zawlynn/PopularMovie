package com.zawlynn.udacity.popularmovie.data.network;

import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
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

}
