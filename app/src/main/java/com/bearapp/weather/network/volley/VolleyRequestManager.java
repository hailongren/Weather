package com.bearapp.weather.network.volley;

import com.android.volley.Request;
import com.bearapp.weather.WeatherApp;
import com.bearapp.weather.manager.RequestQueueManager;
import com.bearapp.weather.network.CallBack;


/**
 * Created by Henry.Ren on 8/28/16.
 */
public class VolleyRequestManager {

    private static VolleyRequestManager instance;

    private VolleyRequestManager() {

    }

    public synchronized static VolleyRequestManager getInstance() {
        if (instance == null) {
            instance = new VolleyRequestManager();
        }
        return instance;
    }

    public <T> void get(String url, CallBack<T> callBack) {
        VolleyResponseListener<T> volleyResponseListener = new VolleyResponseListener<>(callBack);
        VolleyRequest<T> volleyRequest = new VolleyRequest<>(Request.Method.GET, url, volleyResponseListener);
        RequestQueueManager.getInstance(WeatherApp.getWeatherApp()).startRequest(volleyRequest);
    }
}
