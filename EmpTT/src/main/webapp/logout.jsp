<!DOCTYPE html>
<html>
<head>
    <title>Logout</title>
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
            margin-bottom: 20px;
            font-size: 16px;
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
        }
        .button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h2>You have been logged out</h2>
    <p>Thank you for using the task tracking system.</p>
    <a href="Login.jsp" class="button">Click here to login again</a>
</body>
</html>
