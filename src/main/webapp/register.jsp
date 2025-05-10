<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp"%>
<html>
<head>
    <title>Register | FreshCart</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f8f9fa;
        }
        .register-container {
            max-width: 400px;
            margin: 100px auto;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
            background-color: white;
        }
        .register-title {
            color: #198754;
            margin-bottom: 30px;
            text-align: center;
        }
        .btn-register {
            background-color: #198754;
            border: none;
            width: 100%;
            padding: 10px;
            margin-top: 10px;
        }
        .form-label {
            font-weight: 500;
        }
        .login-link {
            color: #198754;
            text-decoration: none;
        }
        .login-link:hover {
            text-decoration: underline;
        }
        .password-match {
            font-size: 0.8rem;
            height: 20px;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="register-container">
        <h2 class="register-title">Create Account</h2>
        <form action="register" method="post" id="registrationForm">
            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text" class="form-control" id="username" name="username" required>
            </div>

            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>

            <div class="mb-3">
                <label for="confirm_password" class="form-label">Confirm Password</label>
                <input type="password" class="form-control" id="confirm_password" name="confirm_password" required>
                <div id="passwordMatch" class="password-match text-danger"></div>
            </div>

            <button type="submit" class="btn btn-register btn-success">Register</button>

            <div class="mt-3 text-center">
                <p>Already have an account? <a href="index.jsp" class="login-link">Login here</a></p>
            </div>
        </form>
    </div>
</div>


<script src="js/bootstrap.bundle.min.js"></script>

<script>
    document.getElementById('registrationForm').addEventListener('submit', function(e) {
        const password = document.getElementById('password').value;
        const confirmPassword = document.getElementById('confirm_password').value;
        const matchElement = document.getElementById('passwordMatch');

        if (password !== confirmPassword) {
            e.preventDefault();
            matchElement.textContent = 'Passwords do not match!';
        } else {
            matchElement.textContent = '';
        }
    });


    document.getElementById('confirm_password').addEventListener('input', function() {
        const password = document.getElementById('password').value;
        const confirmPassword = this.value;
        const matchElement = document.getElementById('passwordMatch');

        if (confirmPassword.length > 0) {
            if (password !== confirmPassword) {
                matchElement.textContent = 'Passwords do not match!';
            } else {
                matchElement.textContent = 'Passwords match!';
                matchElement.classList.remove('text-danger');
                matchElement.classList.add('text-success');
            }
        } else {
            matchElement.textContent = '';
        }
    });
</script>
</body>
</html>
