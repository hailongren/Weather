package com.bearapp.weather.splash;

import com.bearapp.weather.base.Location;
import com.bearapp.weather.manager.Constant;
import com.bearapp.weather.manager.GlobalDataManager;
import com.bearapp.weather.model.Weather;
import com.bearapp.weather.network.CallBack;
import com.bearapp.weather.network.volley.VolleyRequestManager;

/**
 * Created by Henry.Ren on 8/27/16.
 */
public class LoadWeatherDataServiceImpl implements LoadWeatherDataService {

    private VolleyRequestManager volleyRequestManager = VolleyRequestManager.getInstance();

    private static final String URL = "http://api.heweather.com/x3/weather?key=e7d267280b87459bba5e62a83b11b8d2";


    @Override
    public void loadWeatherData(CallBack<Weather> onLoadWeatherDataListener) {
        Location location = GlobalDataManager.getInstance().getLocation();
        String url = URL + "&city=" + ((location == null) ? Constant.DEFAULT_CITY : location.getCity());
        volleyRequestManager.get(url, onLoadWeatherDataListener);
    }
}
