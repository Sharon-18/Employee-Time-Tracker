<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Task Charts</title>
    <script>
        function redirectToChart(chartType) {
            if (chartType === 'pie') {
                window.location.href = 'piechart.jsp';
            } else if (chartType === 'bar') {
                window.location.href = 'barchart.jsp';
            }
        }
    </script>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-color: #fce4ec; /* Light pink background */
        }
        .container {
            width: 80%;
            padding: 20px;
            border: 1px solid #f8bbd0; /* Slightly darker pink border */
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            background-color: #ffffff; /* White background for the content */
            text-align: center;
        }
        h2 {
            color: #d81b60; /* Pink color for header */
        }
        .chart-buttons {
            margin-bottom: 20px;
        }
        .chart-buttons button {
            padding: 10px 20px;
            margin: 0 10px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            background-color: #e91e63; /* Pink color */
            color: #fff;
            font-weight: bold;
        }
        .chart-buttons button:hover {
            background-color: #d81b60; /* Darker pink */
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
            border-radius: 5px;
            font-weight: bold;
        }
        .back-button:hover {
            background-color: #d81b60; /* Darker pink */
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Task Charts</h2>
        <div class="chart-buttons">
            <button onclick="redirectToChart('pie')">Show Pie Chart</button>
            <button onclick="redirectToChart('bar')">Show Bar Charts</button>
        </div>
        <a href="adminDashboard.jsp" class="back-button">Back to Dashboard</a>
    </div>
</body>
</html>
