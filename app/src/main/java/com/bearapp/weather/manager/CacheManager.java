package com.bearapp.weather.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.bearapp.weather.WeatherApp;
import com.bearapp.weather.base.Location;
import com.bearapp.weather.model.HeWeatherData;
import com.bearapp.weather.model.Weather;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by Henry.Ren on 8/29/16.
 */
public class CacheManager {

    public static void saveWeatherToLocal(Weather weather) {
        if (weather != null) {
            SharedPreferences sharedPreferences = WeatherApp.getWeatherApp().getSharedPreferences(Constant.PREF_FILE, Context.MODE_PRIVATE);
            if (sharedPreferences != null) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new GsonBuilder().create();
                editor.putString(Constant.KEY_WEATHER_DATA, gson.toJson(weather)).apply();
            }
        }
    }


    public static Weather loadLocalCachedWeatherData() {
        SharedPreferences sharedPreferences = WeatherApp.getWeatherApp().getSharedPreferences(Constant.PREF_FILE, Context.MODE_PRIVATE);
        if (sharedPreferences != null) {
            String weatherStr = sharedPreferences.getString(Constant.KEY_WEATHER_DATA, "");
            if (!TextUtils.isEmpty(weatherStr)) {
                Gson gson = new GsonBuilder().create();
                Weather weather = gson.fromJson(weatherStr, Weather.class);
                if (weather != null) {
                    HeWeatherData weatherData = weather.HeWeatherDataList.get(0);
                    if (weatherData != null) {
                        String updateTime = weatherData.basic.update.loc;
                        DateFormat dateFormat = new SimpleDateFormat(Constant.FORMAT_YYYYMMDDHHMM, Locale.ENGLISH);
                        Date updateTimeDate = null;
                        try {
                            updateTimeDate = dateFormat.parse(updateTime);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if (updateTimeDate != null) {
                            if (System.currentTimeMillis() - updateTimeDate.getTime() >= TimeUnit.HOURS.toMillis(1)) {
                                weather = null;
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.remove(Constant.KEY_WEATHER_DATA).apply();
                            } else {
                                String weatherCity = weatherData.basic.city;
                                Location location = GlobalDataManager.getInstance().getLocation();
                                if (location != null) {
                                    String city = location.getCity();
                                    if (TextUtils.isEmpty(weatherCity) || TextUtils.isEmpty(city) || !city.equalsIgnoreCase(weatherCity)) {
                                        weather = null;
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.remove(Constant.KEY_WEATHER_DATA).apply();
                                    }
                                }
                            }
                        }
                    }
                }
                return weather;
            }
        }
        return null;
    }

    public static void saveLocationToLocal(Location location) {
        if (location != null) {
            SharedPreferences sharedPreferences = WeatherApp.getWeatherApp().getSharedPreferences(Constant.PREF_FILE, Context.MODE_PRIVATE);
            if (sharedPreferences != null) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new GsonBuilder().create();
                editor.putString(Constant.KEY_LOCATION, gson.toJson(location)).apply();
            }
        }
    }

    public static Location loadLocalCachedLocation() {
        SharedPreferences sharedPreferences = WeatherApp.getWeatherApp().getSharedPreferences(Constant.PREF_FILE, Context.MODE_PRIVATE);
        if (sharedPreferences != null) {
            String locationStr = sharedPreferences.getString(Constant.KEY_LOCATION, "");
            if (!TextUtils.isEmpty(locationStr)) {
                Gson gson = new GsonBuilder().create();
                return gson.fromJson(locationStr, Location.class);
            }
        }
        return null;
    }
}
