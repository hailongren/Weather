package com.bearapp.weather.network.volley;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bearapp.weather.network.CallBack;
import com.bearapp.weather.network.NetWorkError;

import java.lang.reflect.Type;

/**
 * Created by Henry.Ren on 8/28/16.
 */
public class VolleyResponseListener<T> implements Response.Listener<T>, Response.ErrorListener {

    private CallBack<T> mCallBack;
    protected Type mModelClassType;

    public VolleyResponseListener(CallBack<T> callBack) {
        this.mCallBack = callBack;
        this.mModelClassType = callBack.mModelClassType;
    }

    public void onBefore() {
        if (mCallBack != null)
            mCallBack.onBefore();
    }

    public void onAfter() {
        if (mCallBack != null)
            mCallBack.onAfter();
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        if (mCallBack != null)
            mCallBack.onError(new NetWorkError());
    }

    @Override
    public void onResponse(T response) {
        if (mCallBack != null)
            mCallBack.onSuccess(response);
    }
}
