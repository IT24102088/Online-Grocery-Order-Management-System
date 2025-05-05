package com.example.onlinegroceryordermanagementsystem;

import jakarta.servlet.http.Part;

import java.io.*;
import java.util.*;

public class TextReaderAndWriter {

    FileWriter writer;
    String filepath;

    TextReaderAndWriter(String path) throws IOException {

        this.filepath = path;

    }

    public boolean writeText(String text) throws IOException {
        try {
            writer = new FileWriter(this.filepath);
            writer.write(text);
            writer.close();
            return true;
        } catch (IOException e) {
            writer.close();
            return false;
        }
    }
    public boolean checkExists(String text) throws IOException {
        ArrayList<String> userList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.filepath));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                userList.add(parts[0]);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading.");
        }

        return userList.contains(text);


    }

    public boolean validUser(String username,String password) throws IOException {

        HashMap<String,String> users = new HashMap<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.filepath));
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

    public boolean writeImage(Part filepart){

        try{
            filepart.write(filepath + File.separator + filepart.getSubmittedFileName());
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
