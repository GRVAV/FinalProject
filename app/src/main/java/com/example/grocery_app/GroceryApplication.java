package com.example.grocery_app;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GroceryApplication extends Application {
    private static String DB_NAME = "db_grocery_list";
    private static final int DB_VERSION = 1;
    private SQLiteOpenHelper helper;

    @Override
    public void onCreate(){
        super.onCreate();
        helper = new SQLiteOpenHelper(this, DB_NAME, null, DB_VERSION) {
            @Override
            public void onCreate(SQLiteDatabase db) {
                db.execSQL("CREATE TABLE IF NOT EXISTS tbl_products(" +
                        "product_name STRING, price DOUBLE, store STRING, calories INT, tax DOUBLE, total DOUBLE)");
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            }
        };
    }

    public void addProduct(String productName, double price, String store, int calories, double tax) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("product_name", productName);
        values.put("price", price);
        values.put("store", store);
        values.put("calories", calories);
        values.put("tax", tax * price);
        values.put("total", price * (tax + 1));
        db.insert("tbl_products", null, values);
        db.close();

        //
        // Toast.makeText(getApplicationContext(), "Record added to database", Toast.LENGTH_SHORT).show();
    }
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tbl_products", null);
        cursor.moveToFirst();


        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("product_name"));
                @SuppressLint("Range") double price = cursor.getDouble(cursor.getColumnIndex("price"));
                @SuppressLint("Range") String store = cursor.getString(cursor.getColumnIndex("store"));
                @SuppressLint("Range") int calories = cursor.getInt(cursor.getColumnIndex("calories"));
                @SuppressLint("Range") double tax = cursor.getDouble(cursor.getColumnIndex("tax"));
                @SuppressLint("Range") double total = cursor.getDouble(cursor.getColumnIndex("total"));

                Product product = new Product(name, price, store, calories, tax, total);
                productList.add(product);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return productList;
    }

    public Product getProductByName(String product_name) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tbl_products WHERE product_name = ?", new String[]{product_name});

        Product product = null;

        if (cursor.moveToFirst()) {
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("product_name"));
            @SuppressLint("Range") double price = cursor.getDouble(cursor.getColumnIndex("price"));
            @SuppressLint("Range") String store = cursor.getString(cursor.getColumnIndex("store"));
            @SuppressLint("Range") int calories = cursor.getInt(cursor.getColumnIndex("calories"));
            @SuppressLint("Range") double tax = cursor.getDouble(cursor.getColumnIndex("tax"));
            @SuppressLint("Range") double total = cursor.getDouble(cursor.getColumnIndex("total"));

            product = new Product(name, price, store, calories, tax, total);
        }

        cursor.close();
        db.close();

        return product;
    }

    public void updateGroceryByName(Product product){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("product_name", product.getName());
        values.put("price", product.getPrice());
        values.put("store", product.getStore());
        values.put("calories", product.getCalories());
        values.put("tax", product.getTax() * product.getPrice());
        values.put("total", product.getPrice() * (product.getTax() + 1));
        db.update("tbl_products", values, "product_name = ?",new String[]{product.getName()});
        db.close();
    }

    public void deleteAllStats(){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("DELETE FROM tbl_products");
    }

}
