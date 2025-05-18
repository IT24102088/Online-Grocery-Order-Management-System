<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.onlinegroceryordermanagementsystem.Product" %>
<html>
<head>
    <title>Edit Product</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <style>
        .form-container {
            max-width: 600px;
            margin: 30px auto;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        .image-preview {
            max-width: 200px;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<%
    Product product = Product.getProduct(request.getParameter("id"));
    if (product == null) {
        response.sendRedirect("adminPanel.jsp");
        return;
    }
%>

<div class="container">
    <div class="form-container bg-white">
        <h2 class="text-center mb-4">Edit Product</h2>

        <form action="editProduct" method="post" enctype="multipart/form-data">
            <input type="hidden" name="productId" value="<%= product.getId() %>">
            <input type="hidden" name="currentImage" value="<%= product.getImageName() %>">

            <div class="mb-3">
                <label class="form-label">Product Name</label>
                <label>
                    <input type="text" class="form-control" name="productName"
                           value="<%= product.getpName() %>" required>
                </label>
            </div>

            <div class="mb-3">
                <label class="form-label">Price ($)</label>
                <label>
                    <input type="number" step="0.01" class="form-control" name="productPrice"
                           value="<%= product.getpPrice() %>" required>
                </label>
            </div>

            <div class="mb-3">
                <label class="form-label">Product Image</label>
                <div class="mb-2">
                    <img src="Images/<%= product.getImageName() %>"
                         class="image-preview img-thumbnail"
                         alt="Current product image">
                    <div class="form-text">Current: <%= product.getImageName() %></div>
                </div>
                <input class="form-control" type="file" name="productImage">
                <small class="text-muted">Leave blank to keep current image</small>
            </div>

            <div class="d-grid gap-2">
                <button type="submit" class="btn btn-primary">Update Product</button>
                <a href="adminPanel.jsp" class="btn btn-outline-secondary">Cancel</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>