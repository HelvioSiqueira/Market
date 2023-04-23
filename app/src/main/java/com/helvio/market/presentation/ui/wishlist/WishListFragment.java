package com.helvio.market.presentation.ui.wishlist;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.helvio.market.R;
import com.helvio.market.databinding.WishListFragmentBinding;
import com.helvio.market.domain.model.Product;
import com.helvio.market.presentation.adapter.ProductsAdapter;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

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

        TextInputEditText textInputEditTextSearch = binding.textInputEditTextSearch;
        productsAdapter = new ProductsAdapter(requireContext());

        setupRecyclerView();

        productsAdapter.submitList(WishListHelper.getWishProductsList());

        imgSandwich.setOnClickListener(item -> {
            drawerLayout.open();
        });

        textInputEditTextSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                List<Product> searchProductsList =
                        WishListHelper.getWishProductsList().stream()
                                .filter(product -> product.getTitle().toUpperCase(Locale.ROOT).contains(editable.toString().toUpperCase()))
                                .collect(Collectors.toList());

                productsAdapter.submitList(searchProductsList);
            }
        });

        productsAdapter.setOnItemClickListener(item -> {
            NavDirections action =
                    WishListFragmentDirections.actionWishListFragmentToProductFragment(item.getId());
            Navigation.findNavController(binding.getRoot()).navigate(action);
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
