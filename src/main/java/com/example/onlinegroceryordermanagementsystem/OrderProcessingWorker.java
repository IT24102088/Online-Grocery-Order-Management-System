package com.example.onlinegroceryordermanagementsystem;

import java.io.IOException;

public class OrderProcessingWorker implements Runnable {
    private final OrderQueue orderQueue;
    private boolean running = true;

    public OrderProcessingWorker(OrderQueue orderQueue) {
        this.orderQueue = orderQueue;
    }

    @Override
    public void run() {
        System.out.println("Order processing worker started");

        while (running) {
            try {
                Orders order = orderQueue.dequeue();
                if (order == null) {
                    break; // Queue processing was stopped
                }

                // Process the order
                processOrder(order);

            } catch (InterruptedException e) {
                System.out.println("Order processing worker interrupted");
                Thread.currentThread().interrupt();
                break;
            } catch (Exception e) {
                System.err.println("Error processing order: " + e.getMessage());
                // You might want to add failed orders to a separate queue
            }
        }

        System.out.println("Order processing worker stopped");
    }

    private void processOrder(Orders order) throws IOException {

        StringBuilder oderData;
        oderData = new StringBuilder();
        oderData.append(order.getOrderId()).append(",");
        oderData.append(order.getDate()).append(",");
        oderData.append(order.getUsername());
        for (Product product: order.getProducts()){
            oderData.append(",");
            oderData.append(product.getId());
        }
        oderData.append("\n");
        TextReaderAndWriter textReaderAndWriter=new TextReaderAndWriter("C:\\Users\\supun\\OneDrive\\Desktop\\New folder (12)\\OnlineGroceryOrderManagementSystem\\data\\orders");
        textReaderAndWriter.writeText(oderData.toString());

        this.orderQueue.deleteFromQueue(order);

    }


    public void stop() {
        running = false;
    }
}
