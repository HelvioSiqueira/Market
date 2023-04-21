package com.helvio.market.presentation.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.helvio.market.R;
import com.helvio.market.data.remote.api.DummyJsonApiImpl;
import com.helvio.market.data.remote.repository.ApiRepository;
import com.helvio.market.databinding.HomeFragmentBinding;
import com.helvio.market.domain.model.Product;
import com.helvio.market.presentation.adapter.ProductsAdapter;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {

    private final DummyJsonApiImpl api = new DummyJsonApiImpl();
    private final ApiRepository repository = new ApiRepository(api);
    private final HomeViewModel viewModel = new HomeViewModel(repository);

    private TabLayout tabLayout;
    private RecyclerView rvProducts;
    private ProductsAdapter productsAdapter;

    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        HomeFragmentBinding binding = HomeFragmentBinding.inflate(inflater);

        this.navController = Navigation.findNavController(container);

        tabLayout = binding.tabLayout;
        rvProducts = binding.rvProducts;

        viewModel.getAllProducts();

        setupRecyclerView();

        viewModel.products.observe(getViewLifecycleOwner(), products -> {
            productsAdapter.submitList(products);
        });

        productsAdapter.setOnItemClickListener(item -> {
            Log.d("HSV", item.title);
        });

        //this.navController.navigate(R.id.action_homeFragment_to_productFragment);

        return binding.getRoot();
    }

    private void setupRecyclerView() {
        productsAdapter = new ProductsAdapter(requireContext());
        rvProducts.setAdapter(productsAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);

        rvProducts.setLayoutManager(gridLayoutManager);
    }
}
