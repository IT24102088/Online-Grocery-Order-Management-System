<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
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


<form method="POST" action="addProducts.jsp">

    <input type="submit" value="Add Product" >

</form>

</body>
</html>
