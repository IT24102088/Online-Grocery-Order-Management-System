<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp"%>
<html>
<head>
    <title>Login | Online Grocery</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f8f9fa;
        }
        .login-container {
            max-width: 400px;
            margin: 100px auto;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
            background-color: white;
        }
        .login-title {
            color: #198754;
            margin-bottom: 30px;
            text-align: center;
        }
        .btn-login {
            background-color: #198754;
            border: none;
            width: 100%;
            padding: 10px;
            margin-top: 10px;
        }
        .form-label {
            font-weight: 500;
        }
        .register-link {
            color: #198754;
            text-decoration: none;
        }
        .register-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="login-container">
        <h2 class="login-title">Welcome Back</h2>
        <form action="verify" method="post">
            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text" class="form-control" id="username" name="username" required>
            </div>

            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>

            <button type="submit" class="btn btn-login btn-success">Login</button>

            <div class="mt-3 text-center">
                <p>Don't have an account? <a href="register.jsp" class="register-link">Register here</a></p>
            </div>
        </form>
    </div>
</div>

<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
