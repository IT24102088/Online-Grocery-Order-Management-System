package com.example.onlinegroceryordermanagementsystem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session=request.getSession();

        Carts cartObject=new Carts((String) session.getAttribute("username"),request.getParameter("productId"),Integer.parseInt(request.getParameter("quantity")));

        String data=cartObject.getUserName()+","+cartObject.getProductId()+","+cartObject.getQuantity()+"\n";

        TextReaderAndWriter textReaderAndWriter=new TextReaderAndWriter("C:\\Users\\supun\\OneDrive\\Desktop\\New folder (12)\\OnlineGroceryOrderManagementSystem\\data\\cart");

        textReaderAndWriter.writeText(data);

        response.sendRedirect("shop.jsp");
    }
}
