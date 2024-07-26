<!DOCTYPE html>
<html>
<head>
    <title>User Dashboard</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <style>
        body {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
            background-color: #f4f4f4; /* Optional: adds a light background color */
        }
        h2 {
            margin-bottom: 20px;
        }
        .button {
            display: inline-block;
            padding: 15px 30px;
            font-size: 16px;
            color: #fff;
            background-color: #007bff;
            text-align: center;
            text-decoration: none;
            border-radius: 5px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.2);
            transition: background-color 0.3s ease;
            margin: 10px;
        }
        .button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h2>User Dashboard</h2>
    <a href="taskDashboard.jsp" class="button">Manage Tasks</a>
    <a href="workHours.jsp" class="button">View Work Hours</a>
    <a href="logout" class="button">Logout</a>
</body>
</html>
