package com.example.onlinegroceryordermanagementsystem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

@WebServlet("/removeCart")
public class removeFromCart extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String userName = req.getParameter("userName");
        String productId = req.getParameter("productId");

        // Load the current cart items
        var cartItems = Carts.getCarts();

        // Remove the matching item
        Iterator<Carts> iterator = cartItems.iterator();
        while (iterator.hasNext()) {
            Carts cart = iterator.next();
            if (cart.getUserName().equals(userName) && cart.getProductId().equals(productId)) {
                iterator.remove();
                break;
            }
        }

        // Write the updated cart back to file (overwriting it)
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Carts.path))) {
            for (Carts cart : cartItems) {
                writer.write(cart.getUserName() + "," + cart.getProductId() + "," + cart.getQuantity());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Failed to update cart file: " + e.getMessage());
        }

        // Redirect to the cart page
        resp.sendRedirect("cart.jsp");
    }
}

