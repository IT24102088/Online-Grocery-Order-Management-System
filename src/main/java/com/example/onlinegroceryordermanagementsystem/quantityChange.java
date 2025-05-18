package com.example.onlinegroceryordermanagementsystem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/changeQuantity")
public class quantityChange extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String quantity = req.getParameter("quantityChange");
        String cartID = req.getParameter("cartId");

        ArrayList<Carts> allCarts=Carts.getCarts();

        if (quantity.equals("1")) {
            for (Carts c : allCarts) {
                if (c.getProductId().equals(cartID)) {
                    int currentQty = c.getQuantity();
                    c.setQuantity(currentQty + 1);
                    break;
                }
            }
        }else{
            for (Carts c : allCarts) {
                if (c.getProductId().equals(cartID)) {
                    int currentQty = c.getQuantity();
                    if(currentQty >= 2){
                        c.setQuantity(currentQty - 1);
                    }
                    break;
                }
            }
        }

        StringBuilder data;
        data = new StringBuilder();

        for(Carts cart:allCarts){
            data.append(cart.getUserName()).append(",").append(cart.getProductId()).append(",").append(cart.getQuantity()).append("\n");
        }

        TextReaderAndWriter textReaderAndWriter;

        textReaderAndWriter=new TextReaderAndWriter("C:\\Users\\supun\\OneDrive\\Desktop\\New folder (12)\\OnlineGroceryOrderManagementSystem\\data\\cart");
        textReaderAndWriter.writeTextInNew(data.toString());

        resp.sendRedirect("cart.jsp");
    }
}
