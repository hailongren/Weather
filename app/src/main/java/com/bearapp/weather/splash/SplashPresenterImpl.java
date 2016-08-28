package com.bearapp.weather.splash;

import com.bearapp.weather.manager.GlobalDataManager;
import com.bearapp.weather.model.Weather;
import com.bearapp.weather.network.CallBack;
import com.bearapp.weather.network.NetWorkError;

/**
 * Created by Henry.Ren on 8/27/16.
 */
public class SplashPresenterImpl implements SplashPresenter {

    private SplashView splashView;
    private LoadWeatherDataService loadWeatherDataService;

    public SplashPresenterImpl(SplashView splashView) {
        this.splashView = splashView;
        this.loadWeatherDataService = new LoadWeatherDataServiceImpl();
    }

    @Override
    public void loadWeatherData() {
        loadWeatherDataService.loadWeatherData(new CallBack<Weather>() {

            @Override
            public void onBefore() {

            }

            @Override
            public void onAfter() {

            }

            @Override
            public void onError(NetWorkError netWorkError) {
                if (splashView != null) {
                    splashView.navigateToHome();
                }
            }

            @Override
            public void onSuccess(Weather weather) {
                GlobalDataManager globalDataManager = GlobalDataManager.getInstance();
                globalDataManager.setWeather(weather);
                if (splashView != null) {
                    splashView.showWeatherData(weather.HeWeatherDataList.get(0).basic.city);
                    splashView.navigateToHome();
                }
            }
        });
    }


    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {
        splashView = null;
    }

    public void onError(NetWorkError netWorkError) {

    }
}
