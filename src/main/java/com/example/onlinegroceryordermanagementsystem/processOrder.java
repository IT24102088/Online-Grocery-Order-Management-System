package com.example.onlinegroceryordermanagementsystem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;


@WebServlet("/processOrder")
public class processOrder extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session=req.getSession();

        ArrayList<Carts> cartList=Carts.getCarts();

        ArrayList<Carts> orderCarts=new ArrayList<>();

        for(Carts cart:cartList){
            if(Objects.equals(cart.getUserName(),session.getAttribute("username"))){
                orderCarts.add(cart);
            }
        }
        cartList.removeIf(cart -> Objects.equals(cart.getUserName(), session.getAttribute("username")));

        System.out.println(cartList);

        ArrayList<Product> productList=new ArrayList<>();

        for(Carts cart:orderCarts){
            productList.add(Product.getProduct(cart.getProductId()));
        }

        LocalDate currentDate = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String dateString = currentDate.format(formatter);

        Orders order=new Orders(productList, dateString,(String) session.getAttribute("username"));

        StringBuilder oderData;
        oderData = new StringBuilder();
        oderData.append(dateString).append(",");
        oderData.append(order.getUsername());
        for (Product product: order.getProducts()){
            oderData.append(",");
            oderData.append(product.getId());
        }

        TextReaderAndWriter textReaderAndWriter;

        textReaderAndWriter=new TextReaderAndWriter("C:\\Users\\supun\\OneDrive\\Desktop\\New folder (12)\\OnlineGroceryOrderManagementSystem\\data\\orders");
        textReaderAndWriter.writeTextInNew(oderData.toString());

        StringBuilder data;
        data = new StringBuilder();

        for(Carts cart:cartList){
            data.append(cart.getUserName()).append(",").append(cart.getProductId()).append(",").append(cart.getQuantity()).append("\n");
        }

        textReaderAndWriter=new TextReaderAndWriter("C:\\Users\\supun\\OneDrive\\Desktop\\New folder (12)\\OnlineGroceryOrderManagementSystem\\data\\cart");
        textReaderAndWriter.writeTextInNew(data.toString());

        resp.sendRedirect("checkout.jsp");

    }
}
