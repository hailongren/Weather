package com.bearapp.weather.model;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;
import java.util.List;


public class Cond implements Serializable{

    @SerializedName("code")
    public String code;

    @SerializedName("txt")
    public String txt;


}