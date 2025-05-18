package com.example.onlinegroceryordermanagementsystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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

    public static void deleteProduct(String id) throws IOException {
        ArrayList<Product> products = Product.getProductsList();
        productsList.removeIf(product -> product.getId().equals(id));

        StringBuilder builder;
        builder=new StringBuilder();
        for (Product product : products) {
            builder.append(product.getId()).append(",").append(product.getpName()).append(",").append(product.getpPrice()).append(",").append(product.getImageName()).append("\n");
        }

        TextReaderAndWriter Writer=new TextReaderAndWriter(filepath);
        Writer.writeTextInNew(builder.toString());

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

    public static ArrayList<Product> mergeSort(ArrayList<Product> products, boolean ascending) {
        if (products.size() <= 1) return products;

        int mid = products.size() / 2;
        ArrayList<Product> left = new ArrayList<>(products.subList(0, mid));
        ArrayList<Product> right = new ArrayList<>(products.subList(mid, products.size()));

        ArrayList<Product> sortedLeft = mergeSort(left, ascending);
        ArrayList<Product> sortedRight = mergeSort(right, ascending);

        return merge(sortedLeft, sortedRight, ascending);
    }

    private static ArrayList<Product> merge(ArrayList<Product> left, ArrayList<Product> right, boolean ascending) {
        ArrayList<Product> merged = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            boolean condition = ascending
                    ? left.get(i).getpPrice() <= right.get(j).getpPrice()
                    : left.get(i).getpPrice() >= right.get(j).getpPrice();

            if (condition) {
                merged.add(left.get(i++));
            } else {
                merged.add(right.get(j++));
            }
        }

        while (i < left.size()) {
            merged.add(left.get(i++));
        }

        while (j < right.size()) {
            merged.add(right.get(j++));
        }

        return merged;
    }


}
