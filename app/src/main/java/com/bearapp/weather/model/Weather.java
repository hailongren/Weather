package com.bearapp.weather.model;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;
import java.util.List;


public class Weather implements Serializable{

    @SerializedName("HeWeather data service 3.0")
    public List<HeWeatherData> HeWeatherDataList;

}