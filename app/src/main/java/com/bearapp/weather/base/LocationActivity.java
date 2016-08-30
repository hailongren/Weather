package com.bearapp.weather.base;

import android.support.v4.content.LocalBroadcastManager;

import com.amap.api.location.AMapLocation;
import com.bearapp.amaplib.location.LocationService;
import com.bearapp.amaplib.location.LocationUtils;
import com.bearapp.weather.manager.CacheManager;
import com.bearapp.weather.manager.GlobalDataManager;

/**
 * Created by Henry.Ren on 8/27/16.
 */
public class LocationActivity extends CheckPermissionsActivity implements OnGetLocationListener {


    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver();
    }

    @Override
    protected void onPermissionGranted() {
        super.onPermissionGranted();
        getLocation();
    }

    protected void getLocation() {
        LocationService.getLocation(this);
    }

    LocationUtils.LocationReceiver locationReceiver = new LocationUtils.LocationReceiver() {

        @Override
        public void onGetLocation(AMapLocation location) {
            if (location.getErrorCode() == 0) {
                Location mLocation = new Location();
                mLocation.setLatitude(location.getLatitude());
                mLocation.setLongitude(location.getLongitude());
                mLocation.setCountry(location.getCountry());
                mLocation.setProvince(location.getProvince());
                mLocation.setCity(location.getCity());
                mLocation.setCityCode(location.getCityCode());
                mLocation.setDistrict(location.getDistrict());
                mLocation.setAddress(location.getAddress());
                CacheManager.saveLocationToLocal(mLocation);
                LocationActivity.this.onGetLocation(mLocation);
            } else {
                Location mLocation = CacheManager.loadLocalCachedLocation();
                LocationActivity.this.onGetLocation(mLocation);
            }
        }
    };


    private void registerReceiver() {
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(locationReceiver, LocationUtils.getLocationIntentFilter());
    }

    private void unregisterReceiver() {
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(locationReceiver);
    }

    @Override
    public void onGetLocation(Location location) {
        GlobalDataManager globalDataManager = GlobalDataManager.getInstance();
        globalDataManager.setLocation(location);
    }


}
