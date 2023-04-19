package com.helvio.market.di;

import com.helvio.market.BuildConfig;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

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

    public Retrofit.Builder provideRetrofit(OkHttpClient okHttpClient){


        return new Retrofit.Builder();
    }
}
