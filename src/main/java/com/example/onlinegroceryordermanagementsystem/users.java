package com.example.onlinegroceryordermanagementsystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class users {

    private String userName;
    private String password;
    private ArrayList<Orders> orders=new ArrayList<>();
    private String role;
    static ArrayList<users> userList = new ArrayList<users>();
    private boolean isBanned=false;
    static final String path = "C:\\Users\\supun\\OneDrive\\Desktop\\New folder (12)\\OnlineGroceryOrderManagementSystem\\data\\usernameAndPasswords.txt";

    users(String userName, String password, String role,String isBanned) {

        this.userName = userName;
        this.password = password;
        this.role = role;
        this.isBanned=Boolean.parseBoolean(isBanned);
    }
    users(String userName, String password,String role,String isBanned,ArrayList<Orders> orders) {

        this.userName = userName;
        this.password = password;
        this.role = role;
        this.orders = orders;
        this.isBanned=Boolean.parseBoolean(isBanned);


    }

    public boolean isBanned() {
        return isBanned;
    }
    public static boolean isBanned(String username) {

        ArrayList<users> userList=getUserList();
        for(users user:userList){
            if (user.getUserName().equals(username)) {
                return user.isBanned;
            }
        }
        return false;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    public void addOrder(Orders order) {
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

    public ArrayList<Orders> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Orders> orders) {
        this.orders = orders;
    }

    public static String getRole(String userName) {
        ArrayList<users> userList = getUserList();

        for (users user : userList) {
            if (user.getUserName().equals(userName)) {
                return user.getRole();
            }
        }
        return null;
    }

    public static ArrayList<users> getUserList() {
        ArrayList<users> userList = new ArrayList<users>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                userList.add(new users(parts[0],parts[1],parts[2],parts[3],Orders.getOrderByUserName(parts[0])));
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading.");
        }
        return userList;
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

        ArrayList<users> userList = getUserList();
        for (users user : userList) {
            if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;

    }

    public static void removeUser(String userName) throws IOException {
        ArrayList<users> userList = getUserList();
        userList.removeIf(user -> user.getUserName().equals(userName));

        StringBuilder data;
        data = new StringBuilder();

        for(users user:userList){
            data.append(user.getUserName()).append(",").append(user.getPassword()).append(",").append(user.getRole()).append(",").append(String.valueOf(user.isBanned)).append("\n");
        }

        writeData(data.toString());
    }

    public static void changeUserRole(String userName) throws IOException {

        ArrayList<users> userList = getUserList();

        for(users user:userList){
            if (user.getUserName().equals(userName)) {
                switch (user.getRole()) {
                    case "admin":
                        user.setRole("user");
                        break;
                    case "user":
                        user.setRole("admin");
                        break;
                }
                break;
            }
        }
        StringBuilder data;
        data = new StringBuilder();

        for(users user:userList){
            data.append(user.getUserName()).append(",").append(user.getPassword()).append(",").append(user.getRole()).append(",").append(user.isBanned).append("\n");
        }

        writeData(data.toString());


    }

    public static void banUnbanUsers(String userName) throws IOException {
        ArrayList<users> userList = getUserList();
        for(users user:userList){
            if (user.getUserName().equals(userName)) {
                user.setBanned(!user.isBanned());
            }
        }
        StringBuilder data;
        data = new StringBuilder();
        for(users user:userList){
            data.append(user.getUserName()).append(",").append(user.getPassword()).append(",").append(user.getRole()).append(",").append(user.isBanned).append("\n");
        }

        writeData(data.toString());
    }

    public static void writeData(String data) throws IOException {
        TextReaderAndWriter textReaderAndWriter;

        textReaderAndWriter=new TextReaderAndWriter(path);
        textReaderAndWriter.writeTextInNew(data);
    }


}
