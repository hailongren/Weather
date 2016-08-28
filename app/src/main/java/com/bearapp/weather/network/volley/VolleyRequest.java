package com.bearapp.weather.network.volley;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.bearapp.weather.network.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;

/**
 * Created by Henry.Ren on 8/28/16.
 */
public class VolleyRequest<T> extends Request<T> implements HttpRequest {

    private Gson gson = new GsonBuilder().create();

    private VolleyResponseListener<T> volleyResponseListener;

    public VolleyRequest(int method, String url, VolleyResponseListener<T> volleyResponseListener) {
        super(method, url, volleyResponseListener);
        this.volleyResponseListener = volleyResponseListener;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));

            if (volleyResponseListener.mModelClassType == String.class) {
                return Response.success((T) json, HttpHeaderParser.parseCacheHeaders(response));
            } else {
                Object o = gson.fromJson(json, volleyResponseListener.mModelClassType);
                return Response.success((T) o, HttpHeaderParser.parseCacheHeaders(response));
            }
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        onAfterResponse();
        if (volleyResponseListener != null) {
            volleyResponseListener.onResponse(response);
        }
    }

    @Override
    public void deliverError(VolleyError error) {
        onAfterResponse();
        super.deliverError(error);
    }

    @Override
    public Request<?> setRequestQueue(RequestQueue requestQueue) {
        onBeforeRequest();
        return super.setRequestQueue(requestQueue);
    }

    @Override
    public void onBeforeRequest() {
        if (volleyResponseListener != null) {
            volleyResponseListener.onBefore();
        }
    }

    @Override
    public void onAfterResponse() {
        if (volleyResponseListener != null) {
            volleyResponseListener.onAfter();
        }
    }
}
