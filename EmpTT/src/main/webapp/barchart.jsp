<!DOCTYPE html>
<html>
<head>
    <title>Bar Chart</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-color: #f8f9fa;
        }
        .chart-container {
            width: 50%; /* Adjust width as needed */
            max-width: 600px; /* Maximum width */
            margin: auto;
        }
        canvas {
            width: 100% !important; /* Responsive width */
            height: 300px !important; /* Fixed height */
        }
    </style>
</head>
<body>
    <div class="chart-container">
        <h2>Weekly/Monthly Task Distribution</h2>
        <canvas id="barChart"></canvas>
    </div>
    <script>
        var ctx = document.getElementById('barChart').getContext('2d');
        var barChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ['Task 1', 'Task 2', 'Task 3'],
                datasets: [{
                    label: 'Hours',
                    data: [12, 19, 3],
                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
                    borderColor: 'rgba(75, 192, 192, 1)',
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    </script>
</body>
</html>
