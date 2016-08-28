package com.bearapp.weather.model;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;
import java.util.List;


public class Basic implements Serializable{

    @SerializedName("city")
    public String city;

    @SerializedName("cnty")
    public String cnty;

    @SerializedName("id")
    public String id;

    @SerializedName("lat")
    public String lat;

    @SerializedName("lon")
    public String lon;

    @SerializedName("update")
    public Update update;


}