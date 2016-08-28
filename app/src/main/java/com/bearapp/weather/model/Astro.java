package com.bearapp.weather.model;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;
import java.util.List;


public class Astro implements Serializable{

    @SerializedName("sr")
    public String sr;

    @SerializedName("ss")
    public String ss;


}