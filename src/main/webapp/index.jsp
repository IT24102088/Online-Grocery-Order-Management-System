<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp"%>
<html>
<head>
    <title>login </title>
</head>
<body>

    <form action="verify" method="post">

        <label>Username:</label>
        <input type="text" name="username">
        <br>
        <label>Password:</label>
        <input type="password" name="password">
        <br>
        <input type="submit" value="Login">
        <br>
        <label>if u haven't registered yet <a href="register.jsp">Register</a></label>

    </form>




</body>
</html>
