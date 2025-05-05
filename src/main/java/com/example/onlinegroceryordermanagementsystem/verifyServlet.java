package com.example.onlinegroceryordermanagementsystem;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class verifyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();



        TextReaderAndWriter textReaderAndWriter=new TextReaderAndWriter("C:\\Users\\supun\\OneDrive\\Desktop\\New folder (12)\\OnlineGroceryOrderManagementSystem\\data\\usernameAndPasswords.txt");

        String username=req.getParameter("username");
        String password=req.getParameter("password");

        if(textReaderAndWriter.validUser(username,password)){

            session.setAttribute("username",username);

            resp.sendRedirect("shop.jsp");
        }else{
            resp.sendRedirect("index.jsp");
        }

    }
}
