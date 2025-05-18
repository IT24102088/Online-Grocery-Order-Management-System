package com.example.onlinegroceryordermanagementsystem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/deleteProfile")
public class deleteUserProfile extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String deletePassword=request.getParameter("deletePassword");

        if(!deletePassword.equals(session.getAttribute("password"))){
            response.sendRedirect("profile.jsp");
        }

        users.removeUser((String) session.getAttribute("username"));

        Carts.removeCartByUserName((String) session.getAttribute("username"));

        Orders.deleteOrder((String) session.getAttribute("username"));

        response.sendRedirect("logout");

    }
}
