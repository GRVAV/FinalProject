package com.example.grocery_app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class EditGrocery extends AppCompatActivity {

    private Button btnAdd;
    private Button btnReset;
    private EditText txtProductName;
    private EditText txtPrice;
    private EditText txtStore;
    private EditText txtCalories , txttax , txtTotal;
    LinearLayout linearLayout;

    String product_name;
    GroceryApplication groceryApplication;
    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_grocery);

        SharedPreferences sharedPreferences= getSharedPreferences("groceryDetails",MODE_PRIVATE);
        product_name = sharedPreferences.getString("product_name","");
        groceryApplication = (GroceryApplication)getApplicationContext();
        product = groceryApplication.getProductByName(sharedPreferences.getString("product_name",""));

        btnAdd = findViewById(R.id.save_button);
        btnReset = findViewById(R.id.edit_reset_button);
        txtProductName = findViewById(R.id.edit_product_name_edittext);
        txtPrice = findViewById(R.id.edit_price_edittext);
        txtStore = findViewById(R.id.edit_store_edittext);
        txtCalories = findViewById(R.id.edit_calories_edittext);
        txttax = (EditText) findViewById(R.id.edit_tax_edittext);
        txtTotal= (EditText) findViewById(R.id.edit_total_edittext);
        linearLayout = (LinearLayout) findViewById(R.id.linlayEditGroceryActivity);

        txtProductName.setText(product.getName());
        txtStore.setText(product.getStore());
        txtCalories.setText(product.getCalories());
        txtPrice.setText(product.getPrice().toString());
        txttax.setText(product.getTax().toString());
        txtTotal.setText((int) product.getTotal());

        View.OnClickListener btnAddClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                product = new Product(
                        txtProductName.getText().toString(),
                        Double.parseDouble(txtPrice.getText().toString()),
                        txtStore.getText().toString(),
                        Integer.parseInt(txtCalories.getText().toString()),
                        Double.parseDouble(txttax.getText().toString()),
                        Double.parseDouble(txtTotal.getText().toString())

                );
                GroceryApplication app = (GroceryApplication) getApplication();
                app.updateGroceryByName(product);

                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(linearLayout.getWindowToken(), 0);
                Snackbar snackbar = Snackbar.make(linearLayout,"Product: "+txtProductName.getText() +" eupdateddited",Snackbar.LENGTH_LONG);
                snackbar.show();

            }
        };
        btnAdd.setOnClickListener(btnAddClick);
    }


}