<!DOCTYPE html>
<html>
<head>
    <title>Work Hours</title>
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
        p {
            font-size: 18px;
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
    <h2>Total Work Hours</h2>
    <p>Total Hours Worked: <%= request.getAttribute("totalHours") %> hours</p>
    <a href="associateDashboard.jsp" class="button">Back to Dashboard</a>
</body>
</html>
