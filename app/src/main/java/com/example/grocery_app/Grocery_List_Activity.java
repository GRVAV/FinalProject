package com.example.grocery_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.List;


public class Grocery_List_Activity extends AppCompatActivity {
    private GroceryApplication groceryApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        groceryApplication = (GroceryApplication)getApplicationContext();
        List<Product> productList = groceryApplication.getAllProducts();

        RecyclerView recyclerView = findViewById(R.id.recyclerViewProducts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ProductsAdapter(productList));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { //back button on menu screen
        if(item.getItemId() == android.R.id.home) {
            //GO BACK TO YOUR ACTIVITY / exiting the settings activity
            onBackPressed();
        }
        return true;
    }

}