package com.example.onlinegroceryordermanagementsystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class OrderQueue {

    private static final String PATH =
            "C:\\Users\\supun\\OneDrive\\Desktop\\New folder (12)\\OnlineGroceryOrderManagementSystem\\data\\queueData";

    private Orders[] data;
    private int front;
    private int rear;
    private int size;
    private boolean processingEnabled = true;

    private static final int DEFAULT_CAPACITY = 16;

    public OrderQueue() {
        this.data   = new Orders[DEFAULT_CAPACITY];
        this.front  = 0;
        this.rear   = 0;
        this.size   = 0;
    }


    public synchronized void enqueue(Orders order) {
        ensureCapacity(size + 1);

        data[rear] = order;
        rear = (rear + 1) % data.length;
        size++;

        notifyAll();
    }

    public synchronized Orders dequeue() throws InterruptedException {
        while (size == 0 && processingEnabled) {
            wait();
        }
        if (!processingEnabled) {
            return null;
        }

        Orders result = data[front];
        data[front]   = null;
        front         = (front + 1) % data.length;
        size--;

        return result;
    }


    public synchronized void stopProcessing() {
        processingEnabled = false;
        notifyAll();
    }

    public synchronized boolean isEmpty() {
        return size == 0;
    }

    public synchronized int size() {
        return size;
    }


    private void ensureCapacity(int minCapacity) {
        if (minCapacity <= data.length) return;

        int newCap = data.length * 2;
        Orders[] newData = new Orders[newCap];


        for (int i = 0; i < size; i++) {
            newData[i] = data[(front + i) % data.length];
        }

        data  = newData;
        front = 0;
        rear  = size;
    }

    public synchronized void loadOrders() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                ArrayList<Product> products = new ArrayList<>();
                String id       = parts[0];
                String date     = parts[1];
                String username = parts[2];

                String[] remainingItems = Arrays.copyOfRange(parts, 3, parts.length);
                for (String item : remainingItems) {
                    products.add(Product.getProduct(item));
                }
                enqueue(new Orders(id, date, username, products));
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading queue data: " + e.getMessage());
        }
    }

    public synchronized void deleteFromQueue(Orders orderObject) throws IOException {

        ArrayList<Orders> orders = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                ArrayList<Product> products = new ArrayList<>();
                String id       = parts[0];
                String date     = parts[1];
                String username = parts[2];
                String[] remainingItems = Arrays.copyOfRange(parts, 3, parts.length);
                for (String item : remainingItems) {
                    products.add(Product.getProduct(item));
                }
                orders.add(new Orders(id, date, username, products));
            }
        }

        orders.removeIf(order -> order.getOrderId().equals(orderObject.getOrderId()));

        StringBuilder orderData = new StringBuilder();
        for (Orders order : orders) {
            orderData.append(order.getOrderId()).append(',')
                    .append(order.getDate()).append(',')
                    .append(order.getUsername());

            for (Product product : order.getProducts()) {
                orderData.append(',').append(product.getId());
            }
            orderData.append('\n');
        }

        TextReaderAndWriter writer = new TextReaderAndWriter(PATH);
        writer.writeTextInNew(orderData.toString());
    }
}
