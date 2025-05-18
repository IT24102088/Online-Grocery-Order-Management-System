package com.example.onlinegroceryordermanagementsystem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        ArrayList<users> userList=users.getUserList();

        String currentPassword=req.getParameter("currentPassword");
        String newPassword=req.getParameter("newPassword");
        String confirmPassword=req.getParameter("confirmPassword");

        for (users user : userList) {
            if(user.getUserName().equals(session.getAttribute("username"))){
                if(user.getPassword().equals(currentPassword)){
                    if(newPassword.equals(confirmPassword)){
                        user.setPassword(newPassword);
                    }
                }
            }
        }

        StringBuilder data;
        data = new StringBuilder();

        for(users user:userList){
            data.append(user.getUserName()).append(",").append(user.getPassword()).append(",").append(user.getRole()).append("\n");
        }

        TextReaderAndWriter textReaderAndWriter;

        textReaderAndWriter=new TextReaderAndWriter("C:\\Users\\supun\\OneDrive\\Desktop\\New folder (12)\\OnlineGroceryOrderManagementSystem\\data\\usernameAndPasswords.txt");
        textReaderAndWriter.writeTextInNew(data.toString());

        resp.sendRedirect("profile.jsp");


    }
}
