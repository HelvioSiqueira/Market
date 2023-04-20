package com.helvio.market.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.helvio.market.R;
import com.helvio.market.databinding.ItemProductBinding;
import com.helvio.market.domain.model.Product;

public class ProductsAdapter extends ListAdapter<Product, ProductsAdapter.ProductsViewHolder> {

    public Context context;

    public ProductsAdapter(Context context) {
        super(DIFF_CALLBACK);

        this.context = context;
    }

    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);

        return new ProductsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewHolder holder, int position) {
        Product item = getItem(position);
        holder.bind(item, context);
    }

    static class ProductsViewHolder extends RecyclerView.ViewHolder {
        private final ItemProductBinding binding;

        public ProductsViewHolder(View itemView) {
            super(itemView);

            binding = ItemProductBinding.bind(itemView);
        }

        void bind(Product itemProduct, Context context) {

            String firstChar = itemProduct.getTitle().substring(0, 1).toUpperCase();
            String titleCapitalized = firstChar + itemProduct.getTitle().substring(1);

            ImageView imgProduct = binding.imgProduct;

            Glide.with(context).load(itemProduct.getThumbnail()).into(imgProduct);

            binding.txtNameProduct.setText(titleCapitalized);
            binding.txtTypeProduct.setText(itemProduct.getCategory());
            binding.txtPriceProduct.setText(Integer.toString(itemProduct.getPrice()));
        }
    }

    public static final DiffUtil.ItemCallback<Product> DIFF_CALLBACK  = new DiffUtil.ItemCallback<Product>() {
        @Override
        public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.equals(newItem);
        }
    };
}
