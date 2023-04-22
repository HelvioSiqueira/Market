package com.helvio.market.presentation.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;

import com.helvio.market.data.remote.repository.ApiRepository;
import com.helvio.market.domain.model.Product;
import com.helvio.market.domain.model.Products;

import java.util.List;

import javax.inject.Inject;

import dagger.Component;
import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Component
@HiltViewModel
public class HomeViewModel extends ViewModel {

    ApiRepository repository;

    public HomeViewModel(ApiRepository repository){
        this.repository = repository;
    }

    MutableLiveData<List<Product>> products = new MutableLiveData<>();

    public void getAllProducts(){

        Call<Products> call = repository.getProducts();

        call.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(@NonNull Call<Products> call, @NonNull Response<Products> response) {
                if (response.body() != null) {
                    products.setValue(response.body().products);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Products> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
