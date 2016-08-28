package com.bearapp.weather.model;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;
import java.util.List;


public class DailyForecast implements Serializable{

    @SerializedName("astro")
    public Astro astro;

    @SerializedName("cond")
    public Cond cond;

    @SerializedName("date")
    public String date;

    @SerializedName("hum")
    public String hum;

    @SerializedName("pcpn")
    public String pcpn;

    @SerializedName("pop")
    public String pop;

    @SerializedName("pres")
    public String pres;

    @SerializedName("tmp")
    public Tmp tmp;

    @SerializedName("vis")
    public String vis;

    @SerializedName("wind")
    public Wind wind;


}