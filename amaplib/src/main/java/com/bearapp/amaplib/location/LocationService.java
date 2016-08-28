package com.bearapp.amaplib.location;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

public class LocationService extends Service implements AMapLocationListener {

    private AMapLocationClient mLocationClient = null;


    public static void getLocation(Context context) {
        context.startService(new Intent(context, LocationService.class));
    }

    public LocationService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mLocationClient = new AMapLocationClient(this.getApplicationContext());
        mLocationClient.setLocationOption(getDefaultOption());
        mLocationClient.setLocationListener(this);
        mLocationClient.startLocation();
    }

    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(3000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是ture
        mOption.setOnceLocation(true);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(true);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        return mOption;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (null != aMapLocation) {
            mLocationClient.stopLocation();
            Intent intent = new Intent(LocationUtils.ACTION_LOCATION);
            intent.putExtra(LocationUtils.KEY_LOCATION, aMapLocation);
            LocalBroadcastManager.getInstance(this.getApplicationContext()).sendBroadcast(intent);
            LocationService.this.stopSelf();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mLocationClient) {
            mLocationClient.onDestroy();
            mLocationClient = null;
        }
    }
}
