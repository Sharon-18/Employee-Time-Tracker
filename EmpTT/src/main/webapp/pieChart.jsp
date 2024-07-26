<!DOCTYPE html>
<html>
<head>
    <title>Pie Chart</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <link rel="stylesheet" href="styles.css">
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        canvas {
            width: 300px !important;
            height: 300px !important;
        }
    </style>
</head>
<body>
    <div>
        <h2>Daily Task Distribution</h2>
        <canvas id="pieChart"></canvas>
    </div>
    <script>
        new Chart(document.getElementById('pieChart').getContext('2d'), {
            type: 'pie',
            data: {
                labels: ['Task 1', 'Task 2', 'Task 3'],
                datasets: [{
                    data: [10, 20, 30],
                    backgroundColor: ['red', 'blue', 'green']
                }]
            }
        });
    </script>
</body>
</html>
