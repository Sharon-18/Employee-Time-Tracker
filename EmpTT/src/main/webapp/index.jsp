<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-color: #fce4ec; /* Light pink background */
        }
        .login-container {
            width: 300px;
            padding: 20px;
            border: 1px solid #f8bbd0; /* Slightly darker pink border */
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            background-color: #ffffff; /* White background for the form */
        }
        h2 {
            text-align: center;
            color: #d81b60; /* Pink color for header */
        }
        .btn {
            display: block;
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-align: center;
            font-size: 16px;
            color: white;
            text-decoration: none;
        }
        .btn-admin {
            background-color: #e91e63; /* Pink color */
        }
        .btn-associate {
            background-color: #ec407a; /* Lighter pink color */
        }
        .btn-admin:hover {
            background-color: #d81b60; /* Darker pink */
        }
        .btn-associate:hover {
            background-color: #c2185b; /* Darker pink */
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Select Login</h2>
        <form action="adminLogin.jsp" method="get">
            <button type="submit" class="btn btn-admin">Admin Login</button>
        </form>
        <form action="associateDashboard.jsp" method="post">
            <button type="submit" class="btn btn-associate">Associate Login</button>
        </form>
    </div>
</body>
</html>
