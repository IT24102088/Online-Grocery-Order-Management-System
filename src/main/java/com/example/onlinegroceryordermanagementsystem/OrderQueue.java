package com.example.onlinegroceryordermanagementsystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class OrderQueue {

    private static final String PATH="C:\\Users\\supun\\OneDrive\\Desktop\\New folder (12)\\OnlineGroceryOrderManagementSystem\\data\\queueData";

    private static class Node {
        Orders order;
        Node next;

        public Node(Orders order) {
            this.order = order;
            this.next = null;
        }
    }

    private Node front;
    private Node rear;
    private int size;
    private boolean processingEnabled = true;

    public OrderQueue() {
        front = rear = null;
        size = 0;
    }

    public synchronized void enqueue(Orders order) {
        Node newNode = new Node(order);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
        notifyAll(); // Notify waiting worker threads
    }

    public synchronized Orders dequeue() throws InterruptedException {
        while (front == null && processingEnabled) {
            wait(); // Wait for orders if queue is empty
        }

        if (!processingEnabled) {
            return null;
        }

        Node temp = front;
        front = front.next;

        if (front == null) {
            rear = null;
        }

        size--;
        return temp.order;
    }

    public synchronized void stopProcessing() {
        processingEnabled = false;
        notifyAll(); // Wake up any waiting threads
    }

    public synchronized boolean isEmpty() {
        return front == null;
    }

    public synchronized int size() {
        return size;
    }

    public synchronized void loadOrders() {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(PATH));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                ArrayList<Product> products = new ArrayList<>();
                String id = parts[0];
                String date = parts[1];
                String username = parts[2];
                String[] remainingItems = Arrays.copyOfRange(parts, 3, parts.length);
                for(String item : remainingItems) {
                    products.add(Product.getProduct(item));
                }
                this.enqueue(new Orders(id,date, username,products));
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading.");
        }


    }

    public synchronized void deleteFromQueue(Orders orderObject) throws IOException {
        ArrayList<Orders> orders = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(PATH));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                ArrayList<Product> products = new ArrayList<>();
                String id = parts[0];
                String date = parts[1];
                String username = parts[2];
                String[] remainingItems = Arrays.copyOfRange(parts, 3, parts.length);
                for(String item : remainingItems) {
                    products.add(Product.getProduct(item));
                }
                orders.add(new Orders(id,date, username,products));
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading.");
        }

        String username=orderObject.getUsername();

        orders.removeIf(order -> order.getOrderId().equals(orderObject.getOrderId()));

        StringBuilder oderData;
        oderData = new StringBuilder();
        for(Orders order:orders){

            oderData.append(order.getOrderId()).append(",");
            oderData.append(order.getDate()).append(",");
            oderData.append(order.getUsername());

            for (Product product: order.getProducts()){
                oderData.append(",");
                oderData.append(product.getId());
            }
            oderData.append("\n");

        }
        TextReaderAndWriter textReaderAndWriter;

        textReaderAndWriter=new TextReaderAndWriter(PATH);
        textReaderAndWriter.writeTextInNew(oderData.toString());


    }

}