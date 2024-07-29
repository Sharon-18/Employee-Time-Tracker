<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Associate Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #fce4ec; /* Light pink background */
            margin: 0;
            padding: 0;
        }
        .container {
            width: 80%;
            margin: 0 auto;
            padding: 20px;
            background-color: #ffffff; /* White background for the container */
            border: 1px solid #f8bbd0; /* Slightly darker pink border */
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h2 {
            text-align: center;
            color: #d81b60; /* Pink color for header */
        }
        .navigation {
            text-align: center;
            margin: 20px 0;
        }
        .navigation a {
            margin: 0 10px;
            text-decoration: none;
            color: #e91e63; /* Pink color for links */
            font-size: 16px;
            padding: 10px;
            border-radius: 5px;
            display: inline-block;
            transition: background-color 0.3s;
        }
        .navigation a:hover {
            background-color: #ec407a; /* Lighter pink on hover */
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Associate Dashboard</h2>
        <div class="navigation">
            <a href="associatetasklist.jsp">View Tasks</a>
            <a href="chart.jsp">View Charts</a>
            <a href="logout.jsp">Logout</a>
        </div>
    </div>
</body>
</html>
