<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.emp.model.Task" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Task</title>
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

        h1 {
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

        .error {
            color: red;
            margin: 20px;
        }

        .actions {
            margin: 20px 0;
        }

        a {
            color: #e91e63;
            text-decoration: none;
            margin: 20px;
            display: inline-block;
        }
    </style>
</head>
<body>
    <header>
        <h1>Edit Task</h1>
    </header>

    <div class="container">
        <div class="actions">
            <a href="taskList.jsp">Back to Task List</a>
        </div>

        <form action="editTaskAction.jsp" method="post">
            <% 
                Task task = (Task) request.getAttribute("task");
                if (task != null) {
            %>
                <input type="hidden" name="id" value="<%= task.getTaskId() %>">

                <div class="form-group">
                    <label for="employeeId">Employee ID:</label>
                    <input type="number" id="employeeId" name="employeeId" value="<%= task.getUserId() %>" required>
                </div>

                <div class="form-group">
                    <label for="projectId">Project ID:</label>
                    <input type="number" id="projectId" name="projectId" value="<%= task.getProjectId() %>" required>
                </div>

                <div class="form-group">
                    <label for="date">Date:</label>
                    <input type="date" id="date" name="date" value="<%= task.getDate() %>" required>
                </div>

                <div class="form-group">
                    <label for="startTime">Start Time:</label>
                    <input type="time" id="startTime" name="startTime" value="<%= task.getStartTime() %>" required>
                </div>

                <div class="form-group">
                    <label for="endTime">End Time:</label>
                    <input type="time" id="endTime" name="endTime" value="<%= task.getEndTime() %>" required>
                </div>

                <div class="form-group">
                    <label for="category">Category:</label>
                    <input type="text" id="category" name="category" value="<%= task.getCategory() %>" required>
                </div>

                <div class="form-group">
                    <label for="description">Description:</label>
                    <textarea id="description" name="description" rows="4" required><%= task.getDescription() %></textarea>
                </div>

                <div class="form-group">
                    <input type="submit" value="Update Task">
                </div>

            <% 
                } else {
            %>
                <p>No task found to edit.</p>
            <% 
                }
            %>

            <% 
                String errorMessage = request.getParameter("error");
                if (errorMessage != null) {
            %>
                <p class="error"><%= errorMessage %></p>
            <% 
                } 
            %>
        </form>
    </div>
</body>
</html>
