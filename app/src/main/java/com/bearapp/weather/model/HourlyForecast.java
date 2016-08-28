package com.bearapp.weather.model;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;
import java.util.List;


public class HourlyForecast implements Serializable{

    @SerializedName("date")
    public String date;

    @SerializedName("hum")
    public String hum;

    @SerializedName("pop")
    public String pop;

    @SerializedName("pres")
    public String pres;

    @SerializedName("tmp")
    public String tmp;

    @SerializedName("wind")
    public Wind wind;


}