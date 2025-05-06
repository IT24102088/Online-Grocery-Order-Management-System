package com.example.onlinegroceryordermanagementsystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class users {

    private String userName;
    private String password;
    private ArrayList<orders> orders=new ArrayList<>();
    private String role;
    static ArrayList<users> userList = new ArrayList<users>();
    static final String path = "C:\\Users\\supun\\OneDrive\\Desktop\\New folder (12)\\OnlineGroceryOrderManagementSystem\\data\\usernameAndPasswords.txt";

    users(String userName, String password) {

        this.userName = userName;
        this.password = password;
        this.role = "user";

    }

    public void addOrder(orders order) {
        orders.add(order);
    };

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<orders> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<orders> orders) {
        this.orders = orders;
    }

    public static boolean checkExists(String text) throws IOException {
        ArrayList<String> usersNames=new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                usersNames.add(parts[0]);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading.");
        }
        return usersNames.contains(text);

    }
    public static boolean validUser(String username,String password) throws IOException {

        HashMap<String,String> users = new HashMap<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String Username = parts[0];
                String Password = parts[1];
                users.put(Username, Password);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading.");
        }

        for (Map.Entry<String, String> entry : users.entrySet()) {
            if (entry.getKey().equals(username)) {
                if (entry.getValue().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

}
