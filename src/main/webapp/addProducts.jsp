<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="AddProductServlet" method="post" enctype="multipart/form-data">
    <input type="text" name="productName" placeholder="Product Name" required><br>
    <input type="file" name="productImage" accept="image/*" required><br>
    <input type="text" name="productPrice" placeholder="Price" required><br>
    <button type="submit">Add Product</button>
</form>

</body>
</html>
