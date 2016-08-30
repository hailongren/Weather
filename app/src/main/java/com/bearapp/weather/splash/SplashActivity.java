package com.bearapp.weather.splash;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.bearapp.weather.MainActivity;
import com.bearapp.weather.base.Location;
import com.bearapp.weather.base.LocationActivity;

public class SplashActivity extends LocationActivity implements SplashView {

    private SplashPresenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        splashPresenter = new SplashPresenterImpl(this);
    }


    @Override
    public void onGetLocation(Location location) {
        super.onGetLocation(location);
        if (location != null)
            splashPresenter.loadWeatherData();
    }

    @Override
    public void navigateToHome() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showWeatherData(String data) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        splashPresenter.onDestroy();
        super.onDestroy();
    }
}
