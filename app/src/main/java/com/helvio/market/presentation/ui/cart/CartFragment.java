package com.helvio.market.presentation.ui.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.helvio.market.R;
import com.helvio.market.databinding.CartFragmentBinding;

public class CartFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        CartFragmentBinding binding = CartFragmentBinding.inflate(inflater);

        ImageView imgSandwich = binding.imgSandwich;
        DrawerLayout drawerLayout = requireActivity().findViewById(R.id.drawerLayout);

        imgSandwich.setOnClickListener(item -> {
            drawerLayout.open();
        });

        return binding.getRoot();
    }
}
