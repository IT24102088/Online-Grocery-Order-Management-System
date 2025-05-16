package com.example.onlinegroceryordermanagementsystem;

import jakarta.servlet.http.Part;

import java.io.*;

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

    public void writeTextInNew(String text) throws IOException {
        try (PrintWriter writer = new PrintWriter(filepath)) {
            writer.print("");
            writer.print(text);
        } catch (IOException e) {
            System.err.println("Error clearing the file using PrintWriter: " + e.getMessage());
        }
    }

}
