package com.helvio.market.data.remote.api;

import com.helvio.market.BuildConfig;
import com.helvio.market.domain.model.Products;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DummyJsonApiImpl implements DummyJsonApi {

    HttpLoggingInterceptor.Level level =
            BuildConfig.DEBUG ?
                    HttpLoggingInterceptor.Level.BODY :
                    HttpLoggingInterceptor.Level.NONE;

    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor().setLevel(level);

    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(interceptor).build();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    DummyJsonApi api = retrofit.create(DummyJsonApi.class);

    public DummyJsonApiImpl() {
    }

    @Override
    public Call<Products> getProducts() {

        return api.getProducts();
    }
}
