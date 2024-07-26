<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Task Form</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <style>
        body {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
            background-color: #f4f4f4; /* Light background color */
        }
        h2 {
            margin-bottom: 20px;
            color: #333;
        }
        form {
            display: flex;
            flex-direction: column;
            width: 300px;
            gap: 15px; /* Space between form elements */
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.2);
        }
        label {
            font-size: 14px;
            color: #333;
        }
        input, textarea {
            padding: 10px;
            font-size: 14px;
            border: 1px solid #ccc;
            border-radius: 4px;
            width: 100%;
            box-sizing: border-box;
        }
        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            border: none;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
        .button {
            display: inline-block;
            padding: 10px 20px;
            font-size: 14px;
            color: #fff;
            background-color: #007bff;
            text-align: center;
            text-decoration: none;
            border-radius: 4px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.2);
            transition: background-color 0.3s ease;
            margin-top: 20px;
        }
        .button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h2>Task Form</h2>
    <form action="taskManager" method="post">
        <label for="employeeId">Employee ID:</label>
        <input type="number" id="employeeId" name="employeeId" required>
        
        <label for="projectId">Project ID:</label>
        <input type="number" id="projectId" name="projectId" required>
        
        <label for="date">Date:</label>
        <input type="date" id="date" name="date" required>
        
        <label for="startTime">Start Time:</label>
        <input type="time" id="startTime" name="startTime" required>
        
        <label for="endTime">End Time:</label>
        <input type="time" id="endTime" name="endTime" required>
        
        <label for="taskCategory">Task Category:</label>
        <input type="text" id="taskCategory" name="taskCategory" required>
        
        <label for="description">Description:</label>
        <textarea id="description" name="description" rows="4" required></textarea>
        
        <input type="submit" value="Submit">
    </form>
    <a href="associateDashboard.jsp" class="button">Back to Dashboard</a>
</body>
</html>
