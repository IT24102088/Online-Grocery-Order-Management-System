<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp"%>
<%@ page import="com.example.onlinegroceryordermanagementsystem.Product" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
    response.setHeader("Pragma","no-cache");
    response.setHeader("Expires","0");
    if(session.getAttribute("username") == null){
        response.sendRedirect("index.jsp");
    }
%>

<jsp:include page="navbar.jsp" />

Welcome to Online Grocery Order Management System


<%
    for (Product product : Product.readProductDetails()) {
%>
<%= product.getpName() %><b>
<%= product.getpPrice() %><b>
    <img src="Images/<%= product.getImageName() %>" alt="<%= product.getImageName() %>" width="200" height="150" />

<%
    }
%>

</body>
</html>
