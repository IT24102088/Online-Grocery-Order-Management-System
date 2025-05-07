package com.example.onlinegroceryordermanagementsystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class Orders {

    private String orderId;
    private ArrayList<Product> products;
    private String date;
    private String username;
    private static final String filepath="C:\\Users\\supun\\OneDrive\\Desktop\\New folder (12)\\OnlineGroceryOrderManagementSystem\\data\\orders"

    Orders(ArrayList<Product> product, String date, String username) {
        this.orderId = UUID.randomUUID().toString();
        this.products = product;
        this.username=username;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static ArrayList<Orders> getOrders() {
        ArrayList<Orders> orders = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading.");
        }


        return orders;
    }


}
