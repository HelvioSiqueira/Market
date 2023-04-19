package com.helvio.market.di;

import com.helvio.market.BuildConfig;
import com.helvio.market.data.remote.DummyJsonApi;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@dagger.Module
@InstallIn(SingletonComponent.class)
public abstract class Module {

    @Provides
    @Singleton
    public HttpLoggingInterceptor provideLoggingInterceptor() {

        HttpLoggingInterceptor.Level logLevel =
                BuildConfig.DEBUG ?
                        HttpLoggingInterceptor.Level.BODY :
                        HttpLoggingInterceptor.Level.NONE;

        return new HttpLoggingInterceptor().setLevel(logLevel);
    }

    @Provides
    @Singleton
    public OkHttpClient provideHttpClient(HttpLoggingInterceptor interceptor) {

        return new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(interceptor).build();
    }

    @Provides
    @Singleton
    public DummyJsonApi provideRetrofit(OkHttpClient okHttpClient) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dummyjson.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(DummyJsonApi.class);
    }
}
