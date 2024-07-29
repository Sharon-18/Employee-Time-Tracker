<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Task</title>
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
            box-sizing: border-box; /* Ensures padding doesn't affect width */
        }
        textarea {
            resize: vertical; /* Allows users to resize textarea vertically */
            height: 100px; /* Sets a minimum height for the textarea */
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
        .error {
            color: red;
            font-weight: bold;
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Add Task</h2>
        <c:if test="${not empty errorMessage}">
            <p class="error">${errorMessage}</p>
        </c:if>
        <form action="AddTaskServlet" method="post">
            <label for="userId">User ID:</label>
            <input type="number" name="userId" required>
            
            <label for="projectId">Project ID:</label>
            <input type="number" name="projectId" required>
            
            <label for="taskDate">Task Date:</label>
            <input type="date" name="taskDate" required>
            
            <label for="startTime">Start Time:</label>
            <input type="time" name="startTime" required>
            
            <label for="endTime">End Time:</label>
            <input type="time" name="endTime" required>
            
            <label for="taskCategory">Task Category:</label>
            <input type="text" name="taskCategory" required>
            
            <label for="description">Description:</label>
            <textarea name="description" required></textarea>
            
            <label for="duration">Duration (hours):</label>
            <input type="number" step="0.01" min="0" max="8" name="duration" required>
            
            <input type="submit" value="Add Task">
        </form>
    </div>
</body>
</html>
