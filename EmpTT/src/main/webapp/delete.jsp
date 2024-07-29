<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.emp.dao.TaskDAO" %>
<%@ page import="java.sql.SQLException" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Task</title>
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
            text-align: center;
        }
        h2 {
            color: #e91e63; /* Pink color */
        }
        .button {
            background-color: #e91e63; /* Pink color */
            color: #fff;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            font-weight: bold;
            text-decoration: none;
            border-radius: 4px;
        }
        .button:hover {
            background-color: #d81b60; /* Darker pink color */
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Confirm Deletion</h2>
        <% 
            String taskIdStr = request.getParameter("taskId");
            boolean deleted = false;
            if (taskIdStr != null && !taskIdStr.trim().isEmpty()) {
                try {
                    int taskId = Integer.parseInt(taskIdStr);
                    TaskDAO taskDAO = new TaskDAO();
                    deleted = taskDAO.deleteTask(taskId);
                } catch (NumberFormatException e) {
                    // Handle invalid taskId format
                    out.println("<p>Error: Invalid task ID format.</p>");
                    e.printStackTrace();
                } catch (SQLException e) {
                    // Handle SQL exceptions
                    out.println("<p>Error: Failed to delete task due to database issue.</p>");
                    e.printStackTrace();
                }
            } else {
                // Handle missing taskId
                out.println("<p>Error: Task ID is missing.</p>");
            }
        %>
        <p>
            <% if (deleted) { %>
                Task has been successfully deleted.
            <% } else { %>
                Failed to delete the task or invalid task ID.
            <% } %>
            <a href="taskList.jsp" class="button">Go to Task List</a>
        </p>
    </div>
</body>
</html>
