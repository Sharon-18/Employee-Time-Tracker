<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.emp.dao.TaskDAO" %>
<%@ page import="com.emp.model.Task" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Task</title>
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
        form {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }
        label {
            font-weight: bold;
        }
        input, textarea {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="submit"] {
            background-color: #e91e63; /* Pink color */
            color: #fff;
            border: none;
            cursor: pointer;
            font-weight: bold;
            padding: 10px;
            border-radius: 4px;
        }
        input[type="submit"]:hover {
            background-color: #d81b60; /* Darker pink color */
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Edit Task</h2>
        <%
            String taskIdStr = request.getParameter("taskId");
            Task task = null;

            if (taskIdStr != null && !taskIdStr.trim().isEmpty()) {
                try {
                    int taskId = Integer.parseInt(taskIdStr);
                    TaskDAO taskDAO = new TaskDAO();
                    task = taskDAO.getTaskById(taskId);
                } catch (NumberFormatException e) {
                    // Handle invalid taskId format
                    request.setAttribute("errorMessage", "Invalid Task ID.");
                    response.sendRedirect("errorPage.jsp"); // Redirect to an error page
                } catch (Exception e) {
                    // Handle other errors (e.g., database issues)
                    request.setAttribute("errorMessage", "Error retrieving task details.");
                    response.sendRedirect("errorPage.jsp"); // Redirect to an error page
                }
            } else {
                // Handle missing taskId
                request.setAttribute("errorMessage", "Task ID is required.");
                response.sendRedirect("errorPage.jsp"); // Redirect to an error page
            }

            if (task != null) {
        %>
        <form action="EditTaskServlet" method="post">
            <input type="hidden" name="taskId" value="<%= task.getTaskId() %>">
            <label for="userId">User ID:</label>
            <input type="number" name="userId" value="<%= task.getUserId() %>" required>
            
            <label for="projectId">Project ID:</label>
            <input type="number" name="projectId" value="<%= task.getProjectId() %>" required>
            
            <label for="taskDate">Task Date:</label>
            <input type="date" name="taskDate" value="<%= task.getTaskDate().toLocalDate() %>" required>
            
            <label for="startTime">Start Time:</label>
            <input type="time" name="startTime" value="<%= task.getStartTime().toLocalTime() %>" required>
            
            <label for="endTime">End Time:</label>
            <input type="time" name="endTime" value="<%= task.getEndTime().toLocalTime() %>" required>
            
            <label for="taskCategory">Task Category:</label>
            <input type="text" name="taskCategory" value="<%= task.getTaskCategory() %>" required>
            
            <label for="description">Description:</label>
            <textarea name="description" required><%= task.getDescription() %></textarea>
            
            <label for="duration">Duration (hours):</label>
            <input type="number" step="0.01" min="0" max="8" name="duration" value="<%= task.getDuration() %>" required>
            
            <input type="submit" value="Update Task">
        </form>
        <% } %>
    </div>
</body>
</html>
