package com.bearapp.weather.splash;

import com.bearapp.weather.model.Weather;
import com.bearapp.weather.network.CallBack;

/**
 * Created by Henry.Ren on 8/27/16.
 */
public interface LoadWeatherDataService {
    void loadWeatherData(CallBack<Weather> onLoadWeatherDataListener);
}
