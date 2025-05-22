package com.example.onlinegroceryordermanagementsystem;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class OrderProcessingBootstrap implements ServletContextListener {
    private OrderQueue orderQueue;
    private OrderProcessingWorker worker;
    private Thread workerThread;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Initializing Order Processing System");

        // Initialize the order queue
        orderQueue = new OrderQueue();

        // Create and start worker thread
        worker = new OrderProcessingWorker(orderQueue);
        workerThread = new Thread(worker, "OrderProcessingWorker");
        workerThread.start();

        // Make queue available to the application
        sce.getServletContext().setAttribute("orderQueue", orderQueue);

        System.out.println("Order Processing System initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Shutting down Order Processing System");

        // Stop the worker
        if (worker != null) {
            worker.stop();
        }

        // Stop queue processing
        if (orderQueue != null) {
            orderQueue.stopProcessing();
        }

        // Interrupt the worker thread if it's blocked
        if (workerThread != null && workerThread.isAlive()) {
            workerThread.interrupt();
            try {
                workerThread.join(5000); // Wait up to 5 seconds for clean shutdown
            } catch (InterruptedException e) {
                System.err.println("Error during worker thread shutdown: " + e.getMessage());
            }
        }

        System.out.println("Order Processing System shutdown complete");
    }
}
