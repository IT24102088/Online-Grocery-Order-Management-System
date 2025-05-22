package com.example.onlinegroceryordermanagementsystem;

public class OrderQueue {
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
}