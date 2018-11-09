package com.fastie4.etsyclient.presentation.di.module;

import com.fastie4.etsyclient.data.api.ApiInterface;
import com.fastie4.etsyclient.data.api.Constants;
import com.fastie4.etsyclient.presentation.util.EtsyApiInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {
    @Provides
    @Singleton
    ApiInterface apiInterface(Retrofit retrofit) {
        return retrofit.create(ApiInterface.class);
    }

    @Provides
    @Singleton
    Retrofit retrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    @Provides
    @Singleton
    OkHttpClient client() {
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(new EtsyApiInterceptor()
                        .setApiKey(Constants.QUERY_PARAMETER_KEY, Constants.API_KEY)
                        .setRequestsDelay(200))
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BASIC))
                .build();
    }
}