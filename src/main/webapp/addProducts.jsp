<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Product</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .form-container {
            max-width: 600px;
            margin: 30px auto;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
    </style>
</head>
<body>
<div class="container">
    <div class="form-container bg-white">
        <h2 class="text-center mb-4">Add New Product</h2>

        <form action="AddProductServlet" method="post" enctype="multipart/form-data">
            <div class="mb-3">
                <label class="form-label">Product Name</label>
                <input type="text" class="form-control" name="productName" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Description</label>
                <textarea class="form-control" name="productDescription" rows="3"></textarea>
            </div>

            <div class="row">
                <div class="col-md-6 mb-3">
                    <label class="form-label">Price ($)</label>
                    <input type="number" step="0.01" class="form-control" name="productPrice" required>
                </div>
                <div class="col-md-6 mb-3">
                    <label class="form-label">Stock Quantity</label>
                    <input type="number" class="form-control" name="productStock" required>
                </div>
            </div>

            <div class="mb-3">
                <label class="form-label">Category</label>
                <select class="form-select" name="productCategory" required>
                    <option value="">Select a category</option>
                    <option value="Fruits">Fruits</option>
                    <option value="Vegetables">Vegetables</option>
                    <option value="Dairy">Dairy</option>
                    <option value="Meat">Meat</option>
                </select>
            </div>

            <div class="mb-3">
                <label class="form-label">Product Image</label>
                <input class="form-control" type="file" name="productImage" required>
            </div>

            <div class="d-grid gap-2">
                <button type="submit" class="btn btn-primary">Add Product</button>
                <a href="admin.jsp" class="btn btn-outline-secondary">Cancel</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>
