package com.bearapp.weather;

import android.app.Application;

/**
 * Created by Henry.Ren on 8/28/16.
 */
public class WeatherApp extends Application {
    private static WeatherApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }


    public static WeatherApp getWeatherApp() {
        return instance;
    }
}
