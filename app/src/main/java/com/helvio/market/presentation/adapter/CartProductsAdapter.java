package com.helvio.market.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.helvio.market.R;
import com.helvio.market.databinding.ItemProductCartBinding;
import com.helvio.market.domain.model.CartProduct;

public class CartProductsAdapter extends ListAdapter<CartProduct, CartProductsAdapter.CartProductsViewHolder> {

    public Context context;

    public CartProductsAdapter(Context context) {
        super(DIFF_CALLBACK);

        this.context = context;
    }

    @NonNull
    @Override
    public CartProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_cart, parent, false);

        return new CartProductsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartProductsViewHolder holder, int position) {
        CartProduct itemProduct = getItem(position);
        holder.bind(itemProduct, context);
    }

    static class CartProductsViewHolder extends RecyclerView.ViewHolder {

        private final ItemProductCartBinding binding;

        public CartProductsViewHolder(View itemView) {
            super(itemView);

            binding = ItemProductCartBinding.bind(itemView);
        }

        public void bind(CartProduct itemProduct, Context context) {

            Glide.with(context).load(itemProduct.getThumbnail()).into(binding.imgProduct);
            binding.txtNameProduct.setText(itemProduct.getTitle());
            binding.txtBrand.setText(itemProduct.getBrand());
            binding.txtPriceProduct.setText(
                    context.getResources().getString(R.string.txt_price, itemProduct.getPrice())
            );

            binding.cardImgMin.setOnClickListener(item -> {

            });

            binding.cardImgPlus.setOnClickListener(item -> {

            });
        }
    }

    public static final DiffUtil.ItemCallback<CartProduct> DIFF_CALLBACK = new DiffUtil.ItemCallback<CartProduct>() {

        @Override
        public boolean areItemsTheSame(@NonNull CartProduct oldItem, @NonNull CartProduct newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull CartProduct oldItem, @NonNull CartProduct newItem) {
            return oldItem.equals(newItem);
        }
    };

}
