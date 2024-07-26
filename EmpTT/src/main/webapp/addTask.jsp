<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.emp.dao.TaskDAO" %>
<%@ page import="com.emp.model.Task" %>
<%@ page import="com.emp.model.User" %>
<%@ page session="true" %>

<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    String successMessage = request.getParameter("success");
    String errorMessage = request.getParameter("error");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add New Task</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            color: #333;
            margin: 0;
            padding: 0;
        }

        header {
            background-color: #e91e63;
            color: white;
            padding: 10px 20px;
            text-align: center;
        }

        h1, h2 {
            color: #e91e63;
        }

        .container {
            width: 80%;
            margin: auto;
            overflow: hidden;
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }

        .form-group input, .form-group select, .form-group textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }

        .form-group input[type="submit"] {
            background-color: #e91e63;
            color: white;
            border: none;
            cursor: pointer;
            padding: 10px 15px;
        }

        .form-group input[type="submit"]:hover {
            background-color: #d81b60;
        }

        .error, .success {
            color: red;
            margin: 20px;
        }

        .success {
            color: green;
        }

        a {
            color: #e91e63;
            text-decoration: none;
            margin: 20px;
            display: inline-block;
        }

        .actions {
            margin: 20px 0;
        }
    </style>
</head>
<body>
    <header>
        <h1>Welcome, <%= user.getUsername() %></h1>
    </header>

    <div class="container">
        <div class="actions">
            <a href="associateDashboard.jsp">Back to Dashboard</a>
        </div>

        <h2>Add New Task</h2>

        <% if (successMessage != null) { %>
            <p class="success"><%= successMessage %></p>
        <% } %>

        <% if (errorMessage != null) { %>
            <p class="error"><%= errorMessage %></p>
        <% } %>

        <form action="addTaskAction.jsp" method="post">
            <div class="form-group">
                <label for="projectId">Project ID:</label>
                <input type="number" id="projectId" name="projectId" required>
            </div>

            <div class="form-group">
                <label for="date">Date:</label>
                <input type="date" id="date" name="date" required>
            </div>

            <div class="form-group">
                <label for="startTime">Start Time:</label>
                <input type="time" id="startTime" name="startTime" required>
            </div>

            <div class="form-group">
                <label for="endTime">End Time:</label>
                <input type="time" id="endTime" name="endTime" required>
            </div>

            <div class="form-group">
                <label for="category">Category:</label>
                <select id="category" name="category" required>
                    <option value="Development">Development</option>
                    <option value="Testing">Testing</option>
                    <option value="Design">Design</option>
                    <!-- Add more categories as needed -->
                </select>
            </div>

            <div class="form-group">
                <label for="description">Description:</label>
                <textarea id="description" name="description" rows="4" required></textarea>
            </div>

            <div class="form-group">
                <input type="submit" value="Add Task">
            </div>
        </form>
    </div>
</body>
</html>
