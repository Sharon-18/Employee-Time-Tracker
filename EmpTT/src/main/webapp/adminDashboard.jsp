<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 80%;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h2 {
            text-align: center;
            color: #e91e63; /* Pink color */
        }
        .navigation {
            text-align: center;
            margin: 20px 0;
        }
        .navigation a {
            margin: 0 15px;
            text-decoration: none;
            color: #e91e63; /* Pink color */
            font-weight: bold;
        }
        .navigation a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Admin Dashboard</h2>
        <div class="navigation">
            <a href="addTask.jsp">Add Task</a>
            <a href="taskList.jsp">View Tasks</a>
            <a href="chart.jsp">View Charts</a>
            <a href="logout">Logout</a>
        </div>
    </div>
</body>
</html>
