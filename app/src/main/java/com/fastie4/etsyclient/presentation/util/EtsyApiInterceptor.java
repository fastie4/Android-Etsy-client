package com.fastie4.etsyclient.presentation.util;

import android.util.Log;

import java.io.IOException;

import androidx.annotation.NonNull;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class EtsyApiInterceptor implements Interceptor {
    private long lastRequestTimeMillis = 0L;
    private int requestsDelay = 3000;
    private String keyQuery = null;
    private String apiKey = null;

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url()
                .newBuilder()
                .addQueryParameter(keyQuery, apiKey)
                .build();
        request = request.newBuilder().url(url).build();
        long time = System.currentTimeMillis() - lastRequestTimeMillis;
        if (time < requestsDelay) {
            try {
                Log.d("etsyclient", "Too many requests! Delaying...");
                lastRequestTimeMillis += requestsDelay;
                Thread.sleep(requestsDelay - time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            lastRequestTimeMillis = System.currentTimeMillis();
        }
        return chain.proceed(request);
    }

    public EtsyApiInterceptor setRequestsDelay(int requestsDelay) {
        this.requestsDelay = requestsDelay;
        return this;
    }

    public EtsyApiInterceptor setApiKey(String query, String apiKey) {
        this.keyQuery = query;
        this.apiKey = apiKey;
        return this;
    }
}