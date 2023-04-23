package com.helvio.market.presentation.ui.home;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.helvio.market.R;
import com.helvio.market.data.remote.api.DummyJsonApiImpl;
import com.helvio.market.data.remote.repository.ApiRepository;
import com.helvio.market.databinding.ActivityMainBinding;
import com.helvio.market.databinding.HomeFragmentBinding;
import com.helvio.market.domain.model.Product;
import com.helvio.market.domain.model.Products;
import com.helvio.market.presentation.adapter.ProductsAdapter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {

    private final DummyJsonApiImpl api = new DummyJsonApiImpl();
    private final ApiRepository repository = new ApiRepository(api);
    private final HomeViewModel viewModel = new HomeViewModel(repository);

    TextInputEditText textInputEditTextSearch;
    private RecyclerView rvProducts;
    private ProductsAdapter productsAdapter;

    private List<Product> productList = new ArrayList<>();
    private List<Product> brandProductsList = new ArrayList<>();
    private List<Product> searchProductsList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int currentLightMode = requireContext().getResources().getConfiguration().uiMode;

        if (currentLightMode == 33) {
            changeStatusBarColor(R.color.night_gray);
        } else {
            changeStatusBarColor(R.color.white);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        HomeFragmentBinding binding = HomeFragmentBinding.inflate(inflater);

        textInputEditTextSearch = binding.textInputEditTextSearch;
        rvProducts = binding.rvProducts;
        TabLayout tabLayout = binding.tabLayout;
        ImageView imgSandwich = binding.imgSandwich;

        DrawerLayout drawerLayout = requireActivity().findViewById(R.id.drawerLayout);

        viewModel.getAllProducts();

        setupRecyclerView();

        textInputEditTextSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                searchProductsList =
                        brandProductsList.stream()
                                .filter(product -> product.getTitle().contains(editable.toString()))
                                .collect(Collectors.toList());

                productsAdapter.submitList(searchProductsList);
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                changeListOfProducts(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        imgSandwich.setOnClickListener(item -> {
            drawerLayout.open();
        });

        productsAdapter.setOnItemClickListener(item -> {
            NavDirections action =
                    HomeFragmentDirections.actionHomeFragmentToProductFragment(item.getId());
            Navigation.findNavController(binding.getRoot()).navigate(action);
        });

        viewModel.products.observe(getViewLifecycleOwner(), products -> {
            List<Product> appleProducts =
                    products.stream()
                            .filter(product -> product.getBrand().equals("Apple"))
                            .collect(Collectors.toList());

            brandProductsList = appleProducts;
            productList = products;
            productsAdapter.submitList(appleProducts);
        });

        return binding.getRoot();
    }

    @Override
    public void onPause() {
        super.onPause();

        textInputEditTextSearch.setText("");
    }

    private void setupRecyclerView() {
        productsAdapter = new ProductsAdapter(requireContext());
        rvProducts.setAdapter(productsAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);

        rvProducts.setLayoutManager(gridLayoutManager);
    }

    private void changeListOfProducts(int tabPosition) {
        switch (tabPosition) {

            case 0:
                List<Product> appleProducts =
                        productList.stream()
                                .filter(product -> product.getBrand().equals("Apple"))
                                .collect(Collectors.toList());

                textInputEditTextSearch.setText("");
                brandProductsList = appleProducts;
                productsAdapter.submitList(appleProducts);
                break;
            case 1:
                List<Product> samsungProducts =
                        productList.stream()
                                .filter(product -> product.getBrand().equals("Samsung"))
                                .collect(Collectors.toList());

                textInputEditTextSearch.setText("");
                brandProductsList = samsungProducts;
                productsAdapter.submitList(samsungProducts);
                break;
            case 2:
                List<Product> huaweiProducts =
                        productList.stream()
                                .filter(product -> product.getBrand().equals("Huawei"))
                                .collect(Collectors.toList());

                textInputEditTextSearch.setText("");
                brandProductsList = huaweiProducts;
                productsAdapter.submitList(huaweiProducts);
                break;
            case 3:
                List<Product> oppoProducts =
                        productList.stream()
                                .filter(product -> product.getBrand().equals("OPPO"))
                                .collect(Collectors.toList());

                textInputEditTextSearch.setText("");
                brandProductsList = oppoProducts;
                productsAdapter.submitList(oppoProducts);
                break;
        }
    }

    private void changeStatusBarColor(int color) {
        requireActivity().getWindow().setStatusBarColor(
                requireContext().getResources().getColor(color, requireContext().getTheme())
        );
    }

}
