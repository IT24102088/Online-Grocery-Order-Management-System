<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp"%>
<%@ page import="com.example.onlinegroceryordermanagementsystem.Product" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
    <title>Product List</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <style>
        .product-card {
            transition: transform 0.3s;
            height: 100%;
        }
        .product-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 10px 20px rgba(0,0,0,0.1);
        }
        .product-img {
            height: 180px;
            object-fit: contain;
            padding: 10px;
        }
        .card-body {
            display: flex;
            flex-direction: column;
        }
        .price-tag {
            font-weight: bold;
            color: #198754;
            margin-top: auto;
        }
        .sort-dropdown {
            max-width: 250px;
        }
    </style>
</head>
<body class="bg-light">

<script src="js/bootstrap.bundle.min.js"></script>
<%
    response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
    response.setHeader("Pragma","no-cache");
    response.setHeader("Expires","0");
    if(session.getAttribute("username") == null){
        response.sendRedirect("index.jsp");
    }

    ArrayList<Product> products = Product.readProductDetails();
    String sortBy = request.getParameter("sort");


    if(sortBy != null) {
        switch(sortBy) {
            case "price_asc":
                products.sort(Comparator.comparingDouble(Product::getpPrice));
                break;
            case "price_desc":
                products.sort(Comparator.comparingDouble(Product::getpPrice).reversed());
                break;
            case "name":
                products.sort(Comparator.comparing(Product::getpName));
                break;
        }
    }
%>

<jsp:include page="navbar.jsp" />

<div class="container py-5">
    <div class="text-center mb-5">
        <h1 class="display-4">Welcome to Online Grocery</h1>
        <p class="lead text-muted">Browse our fresh products</p>
    </div>

    <!-- Sorting Dropdown -->
    <div class="row mb-4">
        <div class="col-md-6">
            <form method="get" class="d-flex">
                <label>
                    <select name="sort" class="form-select sort-dropdown" onchange="this.form.submit()">
                        <option value="">Sort by...</option>
                        <option value="price_asc" <%= "price_asc".equals(sortBy) ? "selected" : "" %>>Price: Low to High</option>
                        <option value="price_desc" <%= "price_desc".equals(sortBy) ? "selected" : "" %>>Price: High to Low</option>
                        <option value="name" <%= "name".equals(sortBy) ? "selected" : "" %>>Product Name</option>
                    </select>
                </label>
            </form>
        </div>
    </div>

    <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 row-cols-xl-4 g-4">
        <%
            for (Product product : products) {
        %>
        <div class="col">
            <div class="card product-card h-100">
                <img src="Images/<%= product.getImageName() %>"
                     class="card-img-top product-img"
                     alt="<%= product.getpName() %>"
                     onerror="this.src='Images/default-product.png'">
                <div class="card-body">
                    <h5 class="card-title"><%= product.getpName() %></h5>
                    <div class="d-flex justify-content-between align-items-center mt-auto">
                        <span class="price-tag">$<%= String.format("%.2f",product.getpPrice()) %></span>
                        <form action="AddToCartServlet" method="post" style="display: inline;">
                            <input type="hidden" name="productId" value="<%= product.getId() %>">
                            <input type="hidden" name="quantity" value="1">
                            <button type="submit" class="btn btn-sm btn-success">Add to Cart</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <%
            }
        %>
    </div>
</div>

</body>
</html>
