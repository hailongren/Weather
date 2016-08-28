package com.bearapp.weather.model;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;
import java.util.List;


public class Tmp implements Serializable{

    @SerializedName("max")
    public String max;

    @SerializedName("min")
    public String min;


}