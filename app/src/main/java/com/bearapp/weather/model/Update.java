package com.bearapp.weather.model;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;
import java.util.List;


public class Update implements Serializable{

    @SerializedName("loc")
    public String loc;

    @SerializedName("utc")
    public String utc;


}