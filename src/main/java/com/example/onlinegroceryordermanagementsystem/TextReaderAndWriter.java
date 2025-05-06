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
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(this.filepath, true))) {  // ← TRUE for append

            writer.append(text);
            return true;

        } catch (IOException e) {
            System.err.println("Write failed: " + e.getMessage());
            return false;
        }
    }

    public void writeImage(Part filepart){

        try{
            filepart.write(filepath + File.separator + filepart.getSubmittedFileName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
