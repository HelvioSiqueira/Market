package com.helvio.market.presentation.ui.splash;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.helvio.market.databinding.SplashFragmentBinding;

public class SplashFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        SplashFragmentBinding binding = SplashFragmentBinding.inflate(inflater);

        ImageView imgLogo = binding.imgLogo;

        imgLogo.setAlpha(0f);
        imgLogo.animate().setDuration(1500).alpha(1f).withEndAction(() -> {
            NavDirections action = SplashFragmentDirections.actionSplashFragmentToHomeFragment();
            Navigation.findNavController(binding.getRoot()).navigate(action);
        });

        return binding.getRoot();
    }
}
