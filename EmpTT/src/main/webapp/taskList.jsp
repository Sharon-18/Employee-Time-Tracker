<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.emp.model.Task" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Task List</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <style>
        body {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
            background-color: #f4f4f4;
        }
        header {
            background-color: #e91e63;
            color: white;
            padding: 10px 20px;
            text-align: center;
            width: 100%;
        }
        h1 {
            margin: 0;
            color: #e91e63;
        }
        .container {
            width: 80%;
            margin: 20px auto;
            overflow: hidden;
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.2);
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 12px;
            text-align: left;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        .error {
            color: red;
            margin: 20px;
        }
        a {
            color: #e91e63;
            text-decoration: none;
            margin: 10px 0;
            display: inline-block;
        }
    </style>
</head>
<body>
    <header>
        <h1>Task List</h1>
    </header>

    <div class="container">
        <a href="Login.jsp">Go to Home Page</a>
        <a href="addTask.jsp">Add New Task</a>

        <table>
            <thead>
                <tr>
                    <th>Task ID</th>
                    <th>Employee ID</th>
                    <th>Project ID</th>
                    <th>Date</th>
                    <th>Start Time</th>
                    <th>End Time</th>
                    <th>Category</th>
                    <th>Description</th>
                    <th>Actions</th>
                </
