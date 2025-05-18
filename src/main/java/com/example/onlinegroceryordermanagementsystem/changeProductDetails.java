package com.example.onlinegroceryordermanagementsystem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.stream.Collectors;

@WebServlet("/editProduct")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1MB
        maxFileSize = 1024 * 1024 * 5,   // 5MB
        maxRequestSize = 1024 * 1024 * 10 // 10MB
)
public class changeProductDetails extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("productId");

        Part filePart = request.getPart("productImage");
        Part productName = request.getPart("productName");
        Part productPrice = request.getPart("productPrice");
        String imageName = filePart.getSubmittedFileName();

        String pName = new BufferedReader(new InputStreamReader(productName.getInputStream()))
                .lines().collect(Collectors.joining());

        String pPrice = new BufferedReader(new InputStreamReader(productPrice.getInputStream()))
                .lines().collect(Collectors.joining());


        String uploadPath = "C:\\Users\\supun\\OneDrive\\Desktop\\New folder (12)\\OnlineGroceryOrderManagementSystem\\src\\main\\webapp\\Images";
        File imageDir = new File(uploadPath);
        File[] existingImages = imageDir.listFiles();

        boolean imageExists = false;
        assert existingImages != null;
        for (File image : existingImages) {
            if (image.getName().equalsIgnoreCase(imageName)) {
                imageExists = true;
                break;
            }
        }
        TextReaderAndWriter Writer;
        if (!imageExists) {
            Writer= new TextReaderAndWriter("C:\\Users\\supun\\OneDrive\\Desktop\\New folder (12)\\OnlineGroceryOrderManagementSystem\\src\\main\\webapp\\Images");
            Writer.writeImage(filePart);
        }

        ArrayList<Product> products = Product.getProductsList();
        for (Product product : products) {
            if (product.getId().equals(id)) {

                product.setpName(pName);
                product.setpPrice(Double.parseDouble(pPrice));
                product.setImageName(imageName);

            }
        }

        StringBuilder builder;
        builder=new StringBuilder();
        for (Product product : products) {
            builder.append(product.getId()).append(",").append(product.getpName()).append(",").append(product.getpPrice()).append(",").append(product.getImageName()).append("\n");
        }
        
        Writer=new TextReaderAndWriter("C:\\Users\\supun\\OneDrive\\Desktop\\New folder (12)\\OnlineGroceryOrderManagementSystem\\data\\productDetails");
        Writer.writeTextInNew(builder.toString());

        response.sendRedirect("adminPanel.jsp");
    }
}
