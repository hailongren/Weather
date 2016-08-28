package com.bearapp.weather.manager;

import com.bearapp.weather.base.Location;
import com.bearapp.weather.model.Weather;

/**
 * Created by Henry.Ren on 8/27/16.
 */
public class GlobalDataManager {

    private static GlobalDataManager instance;

    private GlobalDataManager() {

    }

    public synchronized static GlobalDataManager getInstance() {
        if (instance == null) {
            instance = new GlobalDataManager();
        }
        return instance;
    }

    private Weather weather;
    private Location location;

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
