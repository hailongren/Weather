package com.bearapp.weather.network;

import java.lang.reflect.Type;

/**
 * Created by Henry.Ren on 8/28/16.
 */
public abstract class CallBack<T> {

    public Type mModelClassType;

    public CallBack() {
        mModelClassType = ClassTypeReflect.getModelClazz(getClass());
    }

    public abstract void onBefore();

    public abstract void onAfter();

    public abstract void onError(NetWorkError netWorkError);

    public abstract void onSuccess(T response);
}
