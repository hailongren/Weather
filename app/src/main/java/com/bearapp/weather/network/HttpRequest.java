package com.bearapp.weather.network;

/**
 * Created by Henry.Ren on 8/28/16.
 */
public interface HttpRequest<T> {
    void onBeforeRequest();
    void onAfterResponse();
}
