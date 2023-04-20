package com.example.grocery_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    private List<Product> productList;

    public ProductsAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.textViewProductName.setText("Name: " + product.getName());
        holder.textViewProductPrice.setText("Price: " + product.getPrice());
        holder.textViewProductStore.setText("Store: " + product.getStore());
        holder.textViewProductCalories.setText("Calories: " + product.getCalories());
        holder.textViewProductTax.setText("Tax : " + product.getTax());
        holder.textViewProductTotal.setText("Total: " + product.getTotal());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewProductName;
        TextView textViewProductPrice;
        TextView textViewProductStore;
        TextView textViewProductCalories;
        TextView textViewProductTax;
        TextView textViewProductTotal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewProductName = itemView.findViewById(R.id.textViewProductName);
            textViewProductPrice = itemView.findViewById(R.id.textViewProductPrice);
            textViewProductStore = itemView.findViewById(R.id.textViewProductStore);
            textViewProductCalories = itemView.findViewById(R.id.textViewProductCalories);
            textViewProductTax = itemView.findViewById(R.id.textViewProductTax);
            textViewProductTotal = itemView.findViewById(R.id.textViewProductTotal);
        }
    }
}