package com.helvio.market.presentation.ui.product;


import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.helvio.market.data.remote.repository.ApiRepository;
import com.helvio.market.domain.model.Product;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductViewModel extends ViewModel {

    ApiRepository repository;

    public ProductViewModel(ApiRepository repository) {
        this.repository = repository;
    }

    MutableLiveData<Product> product = new MutableLiveData<>();

    public void getProductById(int productId) {
        Call<Product> call = repository.getProductsById(productId);

        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(@NonNull Call<Product> call, @NonNull Response<Product> response) {
                if (response.isSuccessful()) {
                    product.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Product> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
