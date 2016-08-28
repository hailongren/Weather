package com.bearapp.weather.manager;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.bearapp.weather.network.HttpRequest;

/**
 * Created by Henry.Ren on 8/28/16.
 */
public class RequestQueueManager {

    private static RequestQueueManager instance;

    private RequestQueue requestQueue;

    private RequestQueueManager(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }

    public synchronized static RequestQueueManager getInstance(Context context) {
        if (instance == null) {
            instance = new RequestQueueManager(context.getApplicationContext());
        }
        return instance;
    }


    public <T> void startRequest(HttpRequest<T> request) {
        if (request instanceof Request) {
            requestQueue.add((Request<T>) request);
        }
    }


}
