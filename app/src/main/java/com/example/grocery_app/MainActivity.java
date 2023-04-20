package com.example.grocery_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private Button btnAdd;
    private Button btnReset;
    private EditText txtProductName;
    private EditText txtPrice;
    private EditText txtStore;
    private EditText txtCalories;
    private double tax = 0.13;
    LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnAdd = findViewById(R.id.add_button);
        btnReset = findViewById(R.id.reset_button);
        txtProductName = findViewById(R.id.product_name_edittext);
        txtPrice = findViewById(R.id.price_edittext);
        txtStore = findViewById(R.id.store_edittext);
        txtCalories = findViewById(R.id.calories_edittext);
        linearLayout = (LinearLayout) findViewById(R.id.linlayMainActivity);

        View.OnClickListener btnAddClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroceryApplication app = (GroceryApplication) getApplication();
                app.addProduct(txtProductName.getText().toString(), Double.parseDouble(txtPrice.getText().toString()), txtStore.getText().toString(), Integer.parseInt(txtCalories.getText().toString()), tax);

                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(linearLayout.getWindowToken(), 0);
                Snackbar snackbar = Snackbar.make(linearLayout,"Product: "+txtProductName.getText() +" added",Snackbar.LENGTH_LONG);
                snackbar.show();
                snackbar.setAction("View", new  View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        SharedPreferences sharedPreferences= getSharedPreferences("groceryDetails",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("product_name",txtProductName.getText().toString());
                        editor.apply();


                        Intent intent = new Intent(MainActivity.this,Grocery_List_Activity.class);
                        startActivity(intent);
                        //overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                    }
                });
            }
        };
        btnAdd.setOnClickListener(btnAddClick);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.grocery_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.grocery_list:
                startActivity(new Intent(getApplicationContext(), Grocery_List_Activity.class));
               // overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                break;
            case R.id.settings:
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
               // overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                break;

            default:
                break;
        }

        return true;
    }


}