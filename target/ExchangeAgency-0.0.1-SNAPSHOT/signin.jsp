<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login | Azure-like Design</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background-image: url('https://unsplash.com/photos/_UeY8aTI6d0/download?force=true');
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
        }

        .header {
            background-color: #0078D4;
            color: white;
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px 20px;
            position: fixed;
            top: 0;
            width: 100%;
            z-index: 1000;
        }

        .logo {
            display: flex;
            align-items: center;
        }

        .logo img {
            width: 40px;
            height: auto;
            margin-right: 10px;
        }

        .company-name {
            font-size: 16px;
			padding-top: 20px;
			padding-bottom: 20px;
			font-size: 20px;
            padding-right: 40px; /* Adjusted padding-right */
        }

        .main-container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: calc(100vh - 60px); /* Adjust based on header height */
            padding-top: 60px; /* Adjust as needed to avoid overlap with header */
        }

        .login-container {
            background-color: rgba(255, 255, 255, 0.9);
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 90%;
            max-width: 400px;
            padding: 20px;
            text-align: center;
        }

        .login-box {
            margin-bottom: 20px;
        }

        h1 {
            font-size: 20px;
            color: #333;
            margin-bottom: 20px;
        }

        form {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        input[type=text], input[type=password] {
            width: 100%;
            padding: 12px;
            margin-bottom: 10px;
            font-size: 16px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }

        button {
            background-color: #0078D4;
            color: white;
            border: none;
            padding: 14px 20px;
            cursor: pointer;
            border-radius: 4px;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #106EBE;
        }

        .signup-link {
            margin-top: 20px;
        }

        .signup-link p {
            font-size: 14px;
        }

        .signup-link a {
            color: #0078D4;
            text-decoration: none;
            transition: color 0.3s ease;
        }

        .signup-link a:hover {
            color: #106EBE;
        }
    </style>
</head>
<body>
    <div class="header">
        <div class="company-name">Mary Exchange Agency</div>
    </div>

    <div class="main-container">
        <div class="login-container">
            <div class="login-box">
                <h1>Sign in to Your Account</h1>
			
                <form action="SignInServlet" method="POST">
                    <input type="text" name="username" placeholder="Username" required>
                    <input type="password" name="password" placeholder="Password" required>
                    <button type="submit">Sign in</button>
                </form>
                <div class="signup-link">
                    <p>Don't have an account? <a href="#" id="signup-link">Sign up now</a></p>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
