package com.example.onlinegroceryordermanagementsystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Product {

    private final String id;
    private String pName;
    private double pPrice;
    private String imageName=null;
    private static ArrayList<Product> productsList = new ArrayList<Product>();
    private static final String filepath = "C:\\Users\\supun\\OneDrive\\Desktop\\New folder (12)\\OnlineGroceryOrderManagementSystem\\data\\productDetails";

    Product(String id,String pName, double pPrice, String imageName) {

        this.id = id;
        this.pName = pName;
        this.pPrice = pPrice;
        this.imageName = imageName;

    }


    public static void setProductsList(ArrayList<Product> productsList) {
        Product.productsList = productsList;
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

    public double getpPrice() {
        return pPrice;
    }

    public void setpPrice(double pPrice) {
        this.pPrice = pPrice;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public static Product getProduct(String id) {
        for(Product product:readProductDetails()){

            if(product.getId().equals(id)){
                return product;
            }
        }
        return null;
    }

    public static ArrayList<Product>readProductDetails(){

        productsList = new ArrayList<Product>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line;

            Product productObject;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                productObject = new Product(parts[0],parts[1],Double.parseDouble(parts[2]),parts[3]);
                productsList.add(productObject);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading.");
        }
        return productsList;
    }

    public String getId() {
        return id;
    }
}
