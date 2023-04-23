package com.helvio.market.presentation.ui.product;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.helvio.market.CartProductHelper;
import com.helvio.market.R;
import com.helvio.market.WishListHelper;
import com.helvio.market.data.remote.api.DummyJsonApiImpl;
import com.helvio.market.data.remote.repository.ApiRepository;
import com.helvio.market.databinding.ProductFragmentBinding;
import com.helvio.market.domain.model.CartProduct;
import com.helvio.market.domain.model.Product;

import java.util.Optional;

public class ProductFragment extends Fragment {

    private final DummyJsonApiImpl api = new DummyJsonApiImpl();
    private final ApiRepository repository = new ApiRepository(api);
    private final ProductViewModel viewModel = new ProductViewModel(repository);

    private ProductFragmentBinding binding;
    ImageView imgHeart;

    private Product receiveProduct;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = ProductFragmentBinding.inflate(inflater);
        ImageView imgBack = binding.imgBack;
        imgHeart = binding.imgHeart;
        Button btnAddToCart = binding.btnAddToCart;

        int productId = ProductFragmentArgs.fromBundle(getArguments()).getProductId();

        viewModel.getProductById(productId);

        imgBack.setOnClickListener(view -> {
            requireActivity().onBackPressed();
        });

        imgHeart.setOnClickListener(view -> {
            disableOrEnableBtnHeart();
        });

        btnAddToCart.setOnClickListener(view -> {
            CartProductHelper.addProductInCart(toCartProduct(receiveProduct));
            Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_SHORT).show();
        });

        viewModel.product.observe(getViewLifecycleOwner(), product -> {
            receiveProduct = product;
            fillProductFragment(product);
            setupImgHeartOnEnter();
        });

        return binding.getRoot();
    }

    private void fillProductFragment(Product product) {
        Log.d("HSV", product.getCategory());

        ImageView imgProduct = binding.imgProduct;

        Glide.with(requireContext()).load(product.getThumbnail()).into(imgProduct);

        binding.txtTitleProduct.setText(product.getTitle());
        binding.txtTypeProduct.setText(product.getCategory());
        binding.txtBrandProduct.setText(product.getBrand());
        binding.txtDescriptionProduct.setText(product.getDescription());
        binding.txtPriceProduct.setText(requireContext().getResources().getString(R.string.txt_price, product.getPrice()));
    }

    private CartProduct toCartProduct(Product product) {

        return new CartProduct(
                product.getId(),
                product.getTitle(),
                product.getPrice(),
                product.getBrand(),
                product.getThumbnail(),
                1
        );
    }

    private void disableOrEnableBtnHeart() {

        Optional<Product> find =
                WishListHelper.getWishProductsList().stream()
                        .filter(p -> p.getId() == receiveProduct.getId())
                        .findAny();

        if (find.isPresent()) {
            int currentLightMode = requireContext().getResources().getConfiguration().uiMode;

            if (currentLightMode == 33) {
                imgHeart.setImageDrawable(getResources().getDrawable(R.drawable.heart_night));
            } else {
                imgHeart.setImageDrawable(getResources().getDrawable(R.drawable.heart));
            }
            WishListHelper.removeProductFromWishList(receiveProduct);
        } else {
            imgHeart.setImageDrawable(getResources().getDrawable(R.drawable.heart_disable));
            WishListHelper.addProductInWishList(receiveProduct);
        }
    }

    private void setupImgHeartOnEnter() {
        Optional<Product> find =
                WishListHelper.getWishProductsList().stream()
                        .filter(p -> p.getId() == receiveProduct.getId())
                        .findAny();

        if (find.isPresent()) {
            imgHeart.setImageDrawable(getResources().getDrawable(R.drawable.heart_disable));
        } else {
            int currentLightMode = requireContext().getResources().getConfiguration().uiMode;

            if (currentLightMode == 33) {
                imgHeart.setImageDrawable(getResources().getDrawable(R.drawable.heart_night));
            } else {
                imgHeart.setImageDrawable(getResources().getDrawable(R.drawable.heart));
            }
        }
    }
}
