package com.helvio.market.presentation.ui.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.helvio.market.R;
import com.helvio.market.databinding.CartFragmentBinding;
import com.helvio.market.presentation.adapter.CartProductsAdapter;

public class CartFragment extends Fragment {

    CartProductsAdapter cartProductsAdapter;

    RecyclerView rvCartProducts;
    TextView txtTotalPrice;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        CartFragmentBinding binding = CartFragmentBinding.inflate(inflater);
        DrawerLayout drawerLayout = requireActivity().findViewById(R.id.drawerLayout);

        ImageView imgSandwich = binding.imgSandwich;
        txtTotalPrice = binding.txtTotalPrice;
        rvCartProducts = binding.rvCartProducts;

        cartProductsAdapter = new CartProductsAdapter(requireContext());

        setupRecyclerView();
        updateTotalPrice();

        cartProductsAdapter.submitList(CartProductHelper.getCartProducts());

        cartProductsAdapter.setOnAddItemListener(item -> {
            CartProductHelper.addProductInCart(item);
            updateTotalPrice();
            cartProductsAdapter.notifyDataSetChanged();
        });

        cartProductsAdapter.setOnRemoveItemListener(item -> {
            CartProductHelper.removeProductInCart(item);
            updateTotalPrice();
            cartProductsAdapter.notifyDataSetChanged();
        });

        imgSandwich.setOnClickListener(item -> {
            drawerLayout.open();
        });

        return binding.getRoot();
    }

    private void updateTotalPrice(){
        txtTotalPrice.setText(
                requireContext()
                        .getResources().getString(R.string.txt_total_price, CartProductHelper.getTotalPriceInCart())
        );
    }

    private void setupRecyclerView() {
        rvCartProducts.setAdapter(cartProductsAdapter);
        rvCartProducts.setLayoutManager(new LinearLayoutManager(requireContext()));
    }
}
