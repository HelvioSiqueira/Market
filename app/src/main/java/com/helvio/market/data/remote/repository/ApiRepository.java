package com.helvio.market.data.remote.repository;

import com.helvio.market.data.remote.api.DummyJsonApi;
import com.helvio.market.domain.model.Products;

import retrofit2.Call;

public class ApiRepository {

    DummyJsonApi api;

    public ApiRepository(DummyJsonApi api){
        this.api = api;
    }

    public Call<Products> getProducts(){
        return api.getProducts();
    }
}
