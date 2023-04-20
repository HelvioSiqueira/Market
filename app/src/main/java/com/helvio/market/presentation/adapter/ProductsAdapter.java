package com.helvio.market.presentation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.helvio.market.R;
import com.helvio.market.databinding.ItemProductBinding;
import com.helvio.market.domain.model.Product;

public class ProductsAdapter extends ListAdapter<Product, ProductsAdapter.ProductsViewHolder> {

    public ProductsAdapter() {
        super(DIFF_CALLBACK);
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
        holder.bind(item);
    }

    static class ProductsViewHolder extends RecyclerView.ViewHolder {
        private final ItemProductBinding binding;

        public ProductsViewHolder(View itemView) {
            super(itemView);

            binding = ItemProductBinding.bind(itemView);
        }

        void bind(Product itemProduct) {

            binding.txtNameProduct.setText(itemProduct.getTitle());
            binding.txtTypeProduct.setText(itemProduct.getCategory());
            binding.txtPriceProduct.setText(itemProduct.getPrice());
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
