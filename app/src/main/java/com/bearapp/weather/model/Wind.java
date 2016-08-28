package com.bearapp.weather.model;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;
import java.util.List;


public class Wind implements Serializable{

    @SerializedName("deg")
    public String deg;

    @SerializedName("dir")
    public String dir;

    @SerializedName("sc")
    public String sc;

    @SerializedName("spd")
    public String spd;


}