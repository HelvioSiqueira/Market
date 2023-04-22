package com.helvio.market.presentation.ui.wishlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.helvio.market.R;
import com.helvio.market.WishListHelper;
import com.helvio.market.databinding.WishListFragmentBinding;
import com.helvio.market.presentation.adapter.ProductsAdapter;

public class WishListFragment extends Fragment {

    ProductsAdapter productsAdapter;
    RecyclerView rvWishList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        WishListFragmentBinding binding = WishListFragmentBinding.inflate(inflater);
        DrawerLayout drawerLayout = requireActivity().findViewById(R.id.drawerLayout);

        ImageView imgSandwich = binding.imgSandwich;
        rvWishList = binding.rvWishList;

        productsAdapter = new ProductsAdapter(requireContext());

        setupRecyclerView();

        productsAdapter.submitList(WishListHelper.getWishProductsList());

        imgSandwich.setOnClickListener(item -> {
            drawerLayout.open();
        });

        return binding.getRoot();
    }

    private void setupRecyclerView() {
        productsAdapter = new ProductsAdapter(requireContext());
        rvWishList.setAdapter(productsAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);

        rvWishList.setLayoutManager(gridLayoutManager);
    }
}
