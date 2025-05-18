package com.example.onlinegroceryordermanagementsystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Carts {

    private final String userName;
    private final String productId;
    private int quantity;
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

    public static void removeCartById(String cartID) throws IOException {

        ArrayList<Carts> allCarts=Carts.getCarts();

        allCarts.removeIf(c -> cartID.equals(c.getProductId()));

        StringBuilder data = new StringBuilder();

        for(Carts cart:allCarts){
            data.append(cart.getUserName()).append(",").append(cart.getProductId()).append(",").append(cart.getQuantity()).append("\n");
        }

        TextReaderAndWriter textReaderAndWriter;

        textReaderAndWriter=new TextReaderAndWriter("C:\\Users\\supun\\OneDrive\\Desktop\\New folder (12)\\OnlineGroceryOrderManagementSystem\\data\\cart");
        textReaderAndWriter.writeTextInNew(data.toString());

    }
    public static void removeCartByUserName(String username) throws IOException {

        ArrayList<Carts> allCarts=Carts.getCarts();

        allCarts.removeIf(c -> username.equals(c.getUserName()));

        StringBuilder data = new StringBuilder();

        for(Carts cart:allCarts){
            data.append(cart.getUserName()).append(",").append(cart.getProductId()).append(",").append(cart.getQuantity()).append("\n");
        }

        TextReaderAndWriter textReaderAndWriter;

        textReaderAndWriter=new TextReaderAndWriter("C:\\Users\\supun\\OneDrive\\Desktop\\New folder (12)\\OnlineGroceryOrderManagementSystem\\data\\cart");
        textReaderAndWriter.writeTextInNew(data.toString());

    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
