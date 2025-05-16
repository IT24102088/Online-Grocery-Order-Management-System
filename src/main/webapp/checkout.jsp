<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order Confirmation</title>
    <style>
        .confirmation-container {
            text-align: center;
            margin-top: 50px;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
            max-width: 500px;
            margin-left: auto;
            margin-right: auto;
            background-color: #f9f9f9;
        }
        .btn-continue {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            margin-top: 20px;
        }
        .btn-continue:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<div class="confirmation-container">
    <h2>Order Placed Successfully!</h2>
    <p>Thank you for your purchase. Your order has been received.</p>

    <form action="shop.jsp">
        <button type="submit" class="btn-continue">Continue Shopping</button>
    </form>
</div>
</body>
</html>
