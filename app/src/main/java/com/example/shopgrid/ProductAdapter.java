package com.example.shopgrid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class ProductAdapter extends ListAdapter<Product, ProductAdapter.ProductViewHolder> {

    private static final DiffUtil.ItemCallback<Product> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Product>() {
                @Override
                public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
                    return oldItem.equals(newItem);
                }
            };

    public ProductAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = getItem(position);
        Context context = holder.itemView.getContext();

        holder.productName.setText(product.getName());
        holder.productPrice.setText(context.getString(R.string.price_format, product.getPrice()));
        holder.productImage.setImageResource(product.getImageResId());
        holder.cartButton.setOnClickListener(v ->
                Toast.makeText(context,
                        context.getString(R.string.added_to_cart, product.getName()),
                        Toast.LENGTH_SHORT).show());
        holder.itemView.setOnClickListener(v ->
                Toast.makeText(context, product.getName(), Toast.LENGTH_SHORT).show());
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        final ImageView productImage;
        final ImageView cartButton;
        final TextView productName;
        final TextView productPrice;

        ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            cartButton = itemView.findViewById(R.id.cartButton);
        }
    }
}
