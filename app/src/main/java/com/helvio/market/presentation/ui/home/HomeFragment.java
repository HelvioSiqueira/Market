package com.helvio.market.presentation.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.helvio.market.data.remote.api.DummyJsonApiImpl;
import com.helvio.market.data.remote.repository.ApiRepository;
import com.helvio.market.databinding.HomeFragmentBinding;
import com.helvio.market.presentation.adapter.ProductsAdapter;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {

    DummyJsonApiImpl api = new DummyJsonApiImpl();
    ApiRepository repository = new ApiRepository(api);
    HomeViewModel viewModel = new HomeViewModel(repository);

    TabLayout tabLayout;

    RecyclerView rvProducts;
    ProductsAdapter productsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        HomeFragmentBinding binding = HomeFragmentBinding.inflate(inflater);

        tabLayout = binding.tabLayout;
        rvProducts = binding.rvProducts;

        setupRecyclerView();

        viewModel.getAllProducts();

        viewModel.products.observe(getViewLifecycleOwner(), products -> {
            Log.d("HSV", products.toString());
            productsAdapter.submitList(products);
        });

        return HomeFragmentBinding.inflate(inflater).getRoot();
    }

    private void setupRecyclerView() {
        productsAdapter = new ProductsAdapter();
        rvProducts.setAdapter(productsAdapter);
        rvProducts.setLayoutManager(new GridLayoutManager(requireContext(), 2));
    }
}
