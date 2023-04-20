package com.helvio.market.data.remote.api;

import com.helvio.market.domain.model.Products;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;

public interface DummyJsonApi {

    @GET("products")
    public Call<Products> getProducts();
}
