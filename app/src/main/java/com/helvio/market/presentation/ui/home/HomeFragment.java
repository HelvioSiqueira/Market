package com.helvio.market.presentation.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.helvio.market.databinding.HomeFragmentBinding;
import com.helvio.market.presentation.adapter.ProductsAdapter;

public class HomeFragment extends Fragment {

    TabLayout tabLayout;
    RecyclerView rvProducts;

    ProductsAdapter productsAdapter = new ProductsAdapter();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        HomeFragmentBinding binding = HomeFragmentBinding.inflate(inflater);

        tabLayout = binding.tabLayout;
        rvProducts = binding.rvProducts;

        setupRecyclerView();

        return HomeFragmentBinding.inflate(inflater).getRoot();
    }

    private void setupRecyclerView(){
        rvProducts.setAdapter(productsAdapter);
        rvProducts.setLayoutManager(new LinearLayoutManager(requireContext()));
    }
}
