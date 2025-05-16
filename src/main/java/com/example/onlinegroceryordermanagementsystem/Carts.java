package com.example.onlinegroceryordermanagementsystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Carts {

    private final String userName;
    private final String productId;
    private final int quantity;
    static final String path="C:\\Users\\supun\\OneDrive\\Desktop\\New folder (12)\\OnlineGroceryOrderManagementSystem\\data\\cart";

    static ArrayList<Carts> carts=new ArrayList<>();

    Carts(String userName, String productId, int quantity) {
        this.userName = userName;
        this.productId = productId;

        this.quantity = quantity;
    }

    public String getUserName() {
        return userName;
    }

    public String getProductId() {
        return productId;
    }

    public static void addToCart(Carts cart) {
        carts.add(cart);
    }

    public static ArrayList<Carts> getCarts() {
        carts.clear();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                carts.add(new Carts(parts[0],parts[1],Integer.parseInt(parts[2])));
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading.");
        }

        return carts;
    }

    public int getQuantity() {
        return quantity;
    }
}
