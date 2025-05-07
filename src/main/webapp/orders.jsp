<%@ page import="com.example.onlinegroceryordermanagementsystem.Orders" %>
<%@ page import="com.example.onlinegroceryordermanagementsystem.Product" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Your Orders</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .order-card {
            border: 1px solid #ddd;
            border-radius: 8px;
            padding: 20px;
            margin-bottom: 20px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        .order-header {
            border-bottom: 1px solid #eee;
            padding-bottom: 10px;
            margin-bottom: 15px;
        }
        .product-item {
            display: flex;
            justify-content: space-between;
            padding: 8px 0;
            border-bottom: 1px dashed #eee;
        }
    </style>
</head>
<body>
<%
    response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
    response.setHeader("Pragma","no-cache");
    response.setHeader("Expires","0");

    if(session.getAttribute("username") == null){
        response.sendRedirect("index.jsp");
    }

    String username = (String) session.getAttribute("username");
    ArrayList<Orders> allOrders = Orders.getOrders();
    ArrayList<Orders> userOrders = new ArrayList<>();


    for(Orders order : allOrders) {
        System.out.println(order.getUsername());
        if(order.getUsername().equals(username)) {
            userOrders.add(order);
        }
    }
%>
<jsp:include page="navbar.jsp" />

<div class="container mt-5">
    <h2 class="mb-4">Your Order History</h2>

    <% if(userOrders.isEmpty()) { %>
    <div class="alert alert-info">You haven't placed any orders yet.</div>
    <!-- Debug output -->
    <div class="alert alert-warning mt-3">
        Debug Info:<br>
        Username: <%= username %><br>
        Total Orders in System: <%= allOrders.size() %><br>
        Your Orders: <%= userOrders.size() %>
    </div>
    <% } else { %>
    <% for(Orders order : userOrders) { %>
    <div class="order-card">
        <div class="order-header">
            <div class="d-flex justify-content-between">
                <h5>Order #<%= order.getOrderId() %></h5>
                <span class="text-muted"><%= order.getDate() %></span>
            </div>
        </div>

        <div class="order-products">
            <% for(Product product : order.getProducts()) { %>
            <div class="product-item">
                <span><%= product.getpName() %></span>
                <span>$<%= String.format("%.2f", product.getpPrice()) %></span>
            </div>
            <% } %>
        </div>

        <div class="order-footer mt-3 pt-2 border-top">
            <div class="d-flex justify-content-between fw-bold">
                <span>Total:</span>
                <span>$<%= String.format("%.2f", order.getProducts().stream()
                        .mapToDouble(Product::getpPrice).sum()) %></span>
            </div>
        </div>
    </div>
    <% } %>
    <% } %>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>