package com.example.onlinegroceryordermanagementsystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Product {

    private String pName=null;
    private String pPrice=null;
    private String imageName=null;
    private static ArrayList<Product> productsList = new ArrayList<Product>();
    private static final String filepath = "C:\\Users\\supun\\OneDrive\\Desktop\\New folder (12)\\OnlineGroceryOrderManagementSystem\\data\\productDetails";

    Product(String pName, String pPrice, String imageName) {

        this.pName = pName;
        this.pPrice = pPrice;
        this.imageName = imageName;

    }

    public static ArrayList<Product> getProductsList() {
        return productsList;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpPrice() {
        return pPrice;
    }

    public void setpPrice(String pPrice) {
        this.pPrice = pPrice;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public static ArrayList<Product>readProductDetails(){

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line;

            Product productObject;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                productObject = new Product(parts[0],parts[1],parts[2]);
                productsList.add(productObject);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading.");
        }
        return productsList;
    }

}
