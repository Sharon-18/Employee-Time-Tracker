<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.emp.dao.TaskDAO" %>
<%@ page import="com.emp.model.Task" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Task List</title>
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
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        th, td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #e91e63; /* Pink color */
            color: #fff;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        .back-button {
            display: block;
            width: 150px;
            margin: 20px auto;
            padding: 10px;
            background-color: #e91e63; /* Pink color */
            color: #fff;
            text-align: center;
            text-decoration: none;
            border-radius: 4px;
            font-weight: bold;
        }
        .back-button:hover {
            background-color: #d81b60; /* Darker pink color */
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Task List</h2>
        <table>
            <thead>
                <tr>
                    <th>Task ID</th>
                    <th>User ID</th>
                    <th>Project ID</th>
                    <th>Task Date</th>
                    <th>Start Time</th>
                    <th>End Time</th>
                    <th>Category</th>
                    <th>Description</th>
                    <th>Duration</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    TaskDAO taskDAO = new TaskDAO();
                    List<Task> tasks = taskDAO.getAllTasks();
                    for (Task task : tasks) {
                %>
                <tr>
                    <td><%= task.getTaskId() %></td>
                    <td><%= task.getUserId() %></td>
                    <td><%= task.getProjectId() %></td>
                    <td><%= task.getTaskDate() %></td>
                    <td><%= task.getStartTime() %></td>
                    <td><%= task.getEndTime() %></td>
                    <td><%= task.getTaskCategory() %></td>
                    <td><%= task.getDescription() %></td>
                    <td><%= task.getDuration() %></td>
                </tr>
                <% } %>
            </tbody>
        </table>
        <a href="associateDashboard.jsp" class="back-button">Back to Dashboard</a>
    </div>
</body>
</html>
