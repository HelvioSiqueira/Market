package com.helvio.market.data.remote.api;

import com.helvio.market.domain.model.Product;
import com.helvio.market.domain.model.Products;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DummyJsonApi {

    @GET("products")
    Call<Products> getProducts();

    @GET("products/{id}")
    Call<Product> getProductById(
            @Path("id") int id
    );
}
