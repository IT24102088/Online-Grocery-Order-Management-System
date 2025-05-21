package com.example.onlinegroceryordermanagementsystem;

/**
 * A manual circular queue with blocking dequeue,
 * implemented only with core Java language features.
 */
public class orderQueue<E> {

    private final E[] data;
    private final int capacity;

    private int front = 0;      // index of next item to remove
    private int rear  = 0;      // index to insert into
    private int count = 0;

    @SuppressWarnings("unchecked")
    public orderQueue(int capacity) {
        this.capacity = capacity;
        this.data = (E[]) new Object[capacity];   // generic array creation hack
    }

    /** Add an element to the queue; blocks if the queue is full. */
    public synchronized void enqueue(E item) throws InterruptedException {
        while (count == capacity) {
            wait();                 // queue full ➜ wait for space
        }
        data[rear] = item;
        rear = (rear + 1) % capacity;
        count++;
        notifyAll();                // wake any waiting dequeuer
    }

    /** Remove and return the next element; blocks if queue is empty. */
    public synchronized E dequeue() throws InterruptedException {
        while (count == 0) {
            wait();                 // queue empty ➜ wait for data
        }
        E item = data[front];
        front = (front + 1) % capacity;
        count--;
        notifyAll();                // wake any waiting enqueuer
        return item;
    }

    public synchronized boolean isEmpty() {
        return count == 0;
    }

    public synchronized boolean isFull() {
        return count == capacity;
    }

    public synchronized int size() {
        return count;
    }

    /** Non-blocking peek; returns null if empty */
    public synchronized E peek() {
        return count == 0 ? null : data[front];
    }
}

