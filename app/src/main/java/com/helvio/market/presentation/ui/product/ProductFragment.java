package com.helvio.market.presentation.ui.product;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.helvio.market.R;
import com.helvio.market.WishListHelper;
import com.helvio.market.data.remote.api.DummyJsonApiImpl;
import com.helvio.market.data.remote.repository.ApiRepository;
import com.helvio.market.databinding.ProductFragmentBinding;
import com.helvio.market.domain.model.Product;

public class ProductFragment extends Fragment {

    private final DummyJsonApiImpl api = new DummyJsonApiImpl();
    private final ApiRepository repository = new ApiRepository(api);
    private final ProductViewModel viewModel = new ProductViewModel(repository);

    private ProductFragmentBinding binding;

    private Product receiveProduct;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = ProductFragmentBinding.inflate(inflater);
        ImageView imgBack = binding.imgBack;
        ImageView imgHeart = binding.imgHeart;

        int productId = ProductFragmentArgs.fromBundle(getArguments()).getProductId();

        viewModel.getProductById(productId);

        imgBack.setOnClickListener(view -> {
            requireActivity().onBackPressed();
        });

        imgHeart.setOnClickListener(view -> {
            WishListHelper.addProductInWishList(receiveProduct);
        });

        viewModel.product.observe(getViewLifecycleOwner(), product -> {
            receiveProduct = product;
            fillProductFragment(product);
        });

        return binding.getRoot();
    }

    private void fillProductFragment(Product product){
        Log.d("HSV", product.getCategory());

        ImageView imgProduct = binding.imgProduct;

        Glide.with(requireContext()).load(product.getThumbnail()).into(imgProduct);

        binding.txtTitleProduct.setText(product.getTitle());
        binding.txtTypeProduct.setText(product.getCategory());
        binding.txtBrandProduct.setText(product.getBrand());
        binding.txtDescriptionProduct.setText(product.getDescription());
        binding.txtPriceProduct.setText(requireContext().getResources().getString(R.string.txt_price, product.getPrice()));
    }
}
