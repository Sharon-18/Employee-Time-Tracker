<!DOCTYPE html>
<html>
<head>
    <title>Task Dashboard</title>
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
            margin: 10px 0;
        }
        .button:hover {
            background-color: #0056b3;
        }
        .chart-selector {
            margin-top: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .chart-selector label {
            margin-bottom: 10px;
            font-size: 14px;
        }
        .chart-selector select {
            padding: 10px;
            font-size: 14px;
            border: 1px solid #ccc;
            border-radius: 4px;
            width: 200px;
            box-sizing: border-box;
        }
    </style>
    <script>
        function redirectToChart() {
            var chartType = document.getElementById('chartSelector').value;
            if (chartType) {
                window.location.href = chartType;
            }
        }
    </script>
</head>
<body>
    <h2>Task Dashboard</h2>
    <a href="taskForm.jsp" class="button">Add New Task</a>
    <a href="taskList.jsp" class="button">View Task List</a>

    <div class="chart-selector">
        <label for="chartSelector">View Task Chart:</label>
        <select id="chartSelector" onchange="redirectToChart()">
            <option value="">Select Chart Type</option>
            <option value="pieChart.jsp">Pie Chart</option>
            <option value="barchart.jsp">Bar Chart</option>
        </select>
    </div>

    <a href="logout" class="button">Logout</a>
</body>
</html>
