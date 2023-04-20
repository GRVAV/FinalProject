package com.example.grocery_app;

public class Product {
    private String name;
    private double price;
    private String store;
    private int calories;
    private double tax;
    private double total;

    public Product(String name, double price, String store, int calories, double tax, double total) {
        this.name = name;
        this.price = price;
        this.store = store;
        this.calories = calories;
        this.tax = tax;
        this.total = total;
    }

    public String getName() {
        return name;
    }
    public Double getPrice() {
        return price;
    }
    public String getStore() {
        return store;
    }
    public int getCalories(){
        return calories;
    }
    public Double getTax() {
        return tax;
    }
    public double getTotal(){
        return total;
    }
}
