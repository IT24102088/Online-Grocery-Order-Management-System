package com.example.onlinegroceryordermanagementsystem;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;


public class registerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        TextReaderAndWriter textReaderAndWriter=new TextReaderAndWriter("C:\\Users\\supun\\OneDrive\\Desktop\\New folder (12)\\OnlineGroceryOrderManagementSystem\\data\\usernameAndPasswords.txt");

        if(!users.checkExists(username)){
            if(textReaderAndWriter.writeText(username+","+password+"\n")){

                resp.sendRedirect(req.getContextPath()+"/index.jsp");

            }else {
                System.out.println("something went wrong");
            }
        }else{

            resp.sendRedirect(resp.encodeRedirectURL("register.jsp"));

        }


    }

}
