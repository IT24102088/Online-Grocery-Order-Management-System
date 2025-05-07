<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.onlinegroceryordermanagementsystem.Product" %>
<%@ page import="com.example.onlinegroceryordermanagementsystem.Carts" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.onlinegroceryordermanagementsystem.Carts" %>
<html>
<head>
    <title>Your Shopping Cart</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <style>
        body {
            background-color: #f8f9fa;
            padding-top: 20px;
        }
        .cart-container {
            max-width: 1200px;
            margin: 0 auto;
        }
        .cart-item {
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.05);
            margin-bottom: 20px;
            padding: 20px;
        }
        .product-img {
            max-height: 120px;
            object-fit: contain;
        }
        .quantity-control {
            width: 100px;
        }
        .summary-card {
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.05);
            padding: 20px;
            position: sticky;
            top: 20px;
        }
    </style>
</head>
<body>
<jsp:include page="navbar.jsp" />

<div class="container cart-container py-4">
    <div class="row">
        <div class="col-lg-8">
            <h2 class="mb-4">Your Shopping Cart</h2>

            <%
                String username = (String) session.getAttribute("username");
                List<Product> allProducts = Product.readProductDetails();
                List<Carts> userCarts = new ArrayList<>();
                double total = 0;

                if (username != null) {

                    List<Carts> allCarts = Carts.getCarts();
                    for (Carts cart : allCarts) {
                        if (username.equals(cart.getUserName())) {
                            userCarts.add(cart);
                        }
                    }

                    if (!userCarts.isEmpty()) {
                        for (Carts cart : userCarts) {
                            // Find matching product
                            Product product = null;
                            for (Product p : allProducts) {
                                if (p.getId().equals(cart.getProductId())) {
                                    product = p;
                                    break;
                                }
                            }

                            if (product != null) {
                                total += product.getpPrice() * cart.getQuantity();
            %>
            <div class="cart-item">
                <div class="row align-items-center">
                    <div class="col-md-2">
                        <img src="Images/<%= product.getImageName() != null ? product.getImageName() : "default-product.png" %>"
                             class="product-img img-fluid"
                             alt="<%= product.getpName() %>">
                    </div>
                    <div class="col-md-4">
                        <h5><%= product.getpName() %></h5>
                        <p class="text-muted mb-0">Product ID: <%= product.getId() %></p>
                    </div>
                    <div class="col-md-3">
                        <div class="input-group quantity-control">
                            <button class="btn btn-outline-secondary minus-btn"
                                    type="button"
                                    data-cart-id="<%= cart.getProductId() %>">-</button>
                            <input type="text" class="form-control text-center quantity-input"
                                   value="<%= cart.getQuantity() %>">
                            <button class="btn btn-outline-secondary plus-btn"
                                    type="button"
                                    data-cart-id="<%= cart.getProductId() %>">+</button>
                        </div>
                    </div>
                    <div class="col-md-2 text-end">
                        <h5>$<%= String.format("%.2f", product.getpPrice() * cart.getQuantity()) %></h5>
                    </div>
                    <div class="col-md-1 text-end">
                        <button class="btn btn-danger btn-sm remove-btn"
                                data-cart-id="<%= cart.getProductId() %>">
                            <i class="bi bi-trash"></i>
                        </button>
                    </div>
                </div>
            </div>
            <%
                    }
                }
            } else {
            %>
            <div class="alert alert-info">
                Your cart is empty. <a href="shop.jsp" class="alert-link">Continue shopping</a>
            </div>
            <%
                }
            } else {
            %>
            <div class="alert alert-warning">
                Please <a href="index.jsp" class="alert-link">login</a> to view your cart.
            </div>
            <%
                }
            %>
        </div>

        <div class="col-lg-4">
            <div class="summary-card">
                <h4 class="mb-4">Order Summary</h4>
                <div class="d-flex justify-content-between mb-2">
                    <span>Subtotal:</span>
                    <span>$<%= String.format("%.2f", total) %></span>
                </div>
                <div class="d-flex justify-content-between mb-2">
                    <span>Shipping:</span>
                    <span>$5.00</span>
                </div>
                <hr>
                <div class="d-flex justify-content-between mb-4">
                    <h5>Total:</h5>
                    <h5>$<%= String.format("%.2f", total + 5) %></h5>
                </div>
                <div>
                    <form action="processOrder" method="post" class="d-grid">
                        <button type="submit" class="btn btn-success w-100 py-2">Proceed to Checkout</button>
                    </form>

                    <form action="shop.jsp" method="get" class="d-grid mt-2">
                        <button type="submit" class="btn btn-outline-secondary w-100 py-2">Continue Shopping</button>
                    </form>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap Icons -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
<!-- Bootstrap 5 JS Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<!-- Cart AJAX Script -->
<script>
    document.addEventListener('DOMContentLoaded', function() {
        // Quantity update buttons
        document.querySelectorAll('.minus-btn').forEach(btn => {
            btn.addEventListener('click', function() {
                updateQuantity(this.dataset.cartId, -1);
            });
        });

        document.querySelectorAll('.plus-btn').forEach(btn => {
            btn.addEventListener('click', function() {
                updateQuantity(this.dataset.cartId, 1);
            });
        });

        // Remove item buttons
        document.querySelectorAll('.remove-btn').forEach(btn => {
            btn.addEventListener('click', function() {
                if (confirm('Are you sure you want to remove this item?')) {
                    removeItem(this.dataset.cartId);
                }
            });
        });

        // Quantity input change
        document.querySelectorAll('.quantity-input').forEach(input => {
            input.addEventListener('change', function() {
                const cartId = this.closest('.quantity-control').querySelector('.minus-btn').dataset.cartId;
                const newQuantity = parseInt(this.value);
                updateQuantityDirect(cartId, newQuantity);
            });
        });

        function updateQuantity(cartId, change) {
            const quantityInput = document.querySelector(`.minus-btn[data-cart-id="${cartId}"]`).nextElementSibling;
            const newQuantity = parseInt(quantityInput.value) + change;

            if (newQuantity > 0) {
                sendCartUpdate(cartId, newQuantity);
            }
        }

        function updateQuantityDirect(cartId, newQuantity) {
            if (newQuantity > 0) {
                sendCartUpdate(cartId, newQuantity);
            } else {
                alert('Quantity must be at least 1');
                location.reload();
            }
        }

        function removeItem(cartId) {
            fetch('CartServlet', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: `action=remove&cartId=${cartId}`
            })
                .then(response => {
                    if (response.ok) {
                        location.reload();
                    } else {
                        alert('Failed to remove item');
                    }
                });
        }

        function sendCartUpdate(cartId, quantity) {
            fetch('CartServlet', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: `action=update&cartId=${cartId}&quantity=${quantity}`
            })
                .then(response => {
                    if (response.ok) {
                        location.reload();
                    } else {
                        alert('Failed to update quantity');
                    }
                });
        }
    });
</script>
</body>
</html>
