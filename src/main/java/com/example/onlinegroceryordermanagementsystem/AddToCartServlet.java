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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        Carts cartObject = new Carts(
                (String) session.getAttribute("username"),
                request.getParameter("productId"),
                Integer.parseInt(request.getParameter("quantity"))
        );

        String data = cartObject.getUserName() + "," + cartObject.getProductId() + "," + cartObject.getQuantity() + "\n";

        TextReaderAndWriter textReaderAndWriter = new TextReaderAndWriter(
                "C:\\Users\\User\\OneDrive\\Desktop\\New folder\\Online-Grocery-Order-Management-System-master\\Online-Grocery-Order-Management-System-master\\src\\main\\java\\com\\example\\onlinegroceryordermanagementsystem"
        );

        textReaderAndWriter.writeText(data);

        response.sendRedirect("shop.jsp");
    }
}
