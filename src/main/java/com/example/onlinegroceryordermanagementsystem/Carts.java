package com.example.onlinegroceryordermanagementsystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Carts {

    private final String userName;
    private final String productId;
    private final int quantity;
    static final String path="C:\\Users\\User\\OneDrive\\Desktop\\New folder\\Online-Grocery-Order-Management-System-master\\Online-Grocery-Order-Management-System-master\\src\\main\\java\\com\\example\\onlinegroceryordermanagementsystem";

    static ArrayList<Carts> carts=new ArrayList<>();

    Carts(String userName, String productId, int quantity) {
        this.userName = userName;
        this.productId = productId;

        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public String getUserName() {
        return userName;
    }

    // Static method to add an item to the cart list
    public static void addToCart(Carts cart) {
        carts.add(cart);
    }

    // Static method to retrieve all cart items
    public static ArrayList<Carts> getCarts() {
        carts.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 3) {
                    String userName = parts[0].trim();
                    String productId = parts[1].trim();
                    int quantity;

                    try {
                        quantity = Integer.parseInt(parts[2].trim());
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid quantity format: " + parts[2]);
                        continue;
                    }

                    Carts cartItem = new Carts(userName, productId, quantity);
                    carts.add(cartItem);
                } else {
                    System.err.println("Invalid cart entry format: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the cart file: " + e.getMessage());
        }

        return carts;
    }



    public int getQuantity() {
        return quantity;
    }
}
