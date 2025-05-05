package com.example.onlinegroceryordermanagementsystem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;


import java.io.*;
import java.util.stream.Collectors;


@WebServlet("/AddProductServlet")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class AddProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Part filePart = req.getPart("productImage");
        Part productName = req.getPart("productName");
        Part productPrice = req.getPart("productPrice");
        String imageName = filePart.getSubmittedFileName();

        String pName = new BufferedReader(new InputStreamReader(productName.getInputStream()))
                .lines().collect(Collectors.joining());

        String pPrice = new BufferedReader(new InputStreamReader(productPrice.getInputStream()))
                .lines().collect(Collectors.joining());

        TextReaderAndWriter textWriter=new TextReaderAndWriter("C:\\Users\\supun\\OneDrive\\Desktop\\New folder (12)\\OnlineGroceryOrderManagementSystem\\data\\productDetails");
        textWriter.writeText(pName+","+pPrice+","+imageName+"\n");

        TextReaderAndWriter imageWriter=new TextReaderAndWriter("C:\\Users\\supun\\OneDrive\\Desktop\\New folder (12)\\OnlineGroceryOrderManagementSystem\\data\\Images");
        imageWriter.writeImage(filePart);



    }
}
