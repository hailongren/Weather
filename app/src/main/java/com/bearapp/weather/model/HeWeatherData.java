package com.bearapp.weather.model;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;
import java.util.List;


public class HeWeatherData implements Serializable{

    @SerializedName("basic")
    public Basic basic;

    @SerializedName("daily_forecast")
    public List<DailyForecast> dailyForecast;

    @SerializedName("hourly_forecast")
    public List<HourlyForecast> hourlyForecast;

    @SerializedName("now")
    public Now now;

    @SerializedName("status")
    public String status;


}