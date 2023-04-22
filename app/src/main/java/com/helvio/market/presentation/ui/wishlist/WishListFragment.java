package com.helvio.market.presentation.ui.wishlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.helvio.market.databinding.WishListFragmentBinding;

public class WishListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        WishListFragmentBinding binding = WishListFragmentBinding.inflate(inflater);

        return binding.getRoot();
    }
}
