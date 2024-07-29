<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Weekly and Monthly Tasks Bar Charts</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
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
        .chart-container {
            margin: 20px 0;
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
        <h2>Weekly and Monthly Tasks Bar Charts</h2>
        <div class="chart-container">
            <canvas id="weeklyChart" width="400" height="200"></canvas>
            <canvas id="monthlyChart" width="400" height="200"></canvas>
        </div>

        <script>
            async function fetchData(url) {
                try {
                    const response = await fetch(url);
                    if (!response.ok) throw new Error('Network response was not ok');
                    return response.json();
                } catch (error) {
                    console.error('There has been a problem with your fetch operation:', error);
                    return { labels: [], values: [] }; // Return empty data on error
                }
            }

            async function createCharts() {
                const weeklyData = await fetchData('WeeklyTasksServlet');
                const monthlyData = await fetchData('MonthlyTasksServlet');

                if (weeklyData.labels.length === 0 || weeklyData.values.length === 0) {
                    console.warn('No data available for Weekly Tasks.');
                }

                if (monthlyData.labels.length === 0 || monthlyData.values.length === 0) {
                    console.warn('No data available for Monthly Tasks.');
                }

                // Create Bar Chart for Weekly Tasks
                new Chart(document.getElementById('weeklyChart').getContext('2d'), {
                    type: 'bar',
                    data: {
                        labels: weeklyData.labels || [],
                        datasets: [{
                            label: 'Weekly Tasks',
                            data: weeklyData.values || [],
                            backgroundColor: '#FF6384',
                        }]
                    },
                    options: {
                        responsive: true,
                        plugins: {
                            legend: {
                                position: 'top',
                            },
                            tooltip: {
                                callbacks: {
                                    label: function(tooltipItem) {
                                        return tooltipItem.label + ': ' + tooltipItem.raw + ' tasks';
                                    }
                                }
                            }
                        },
                        scales: {
                            x: {
                                title: {
                                    display: true,
                                    text: 'Week'
                                }
                            },
                            y: {
                                title: {
                                    display: true,
                                    text: 'Number of Tasks'
                                }
                            }
                        }
                    }
                });

                // Create Bar Chart for Monthly Tasks
                new Chart(document.getElementById('monthlyChart').getContext('2d'), {
                    type: 'bar',
                    data: {
                        labels: monthlyData.labels || [],
                        datasets: [{
                            label: 'Monthly Tasks',
                            data: monthlyData.values || [],
                            backgroundColor: '#36A2EB',
                        }]
                    },
                    options: {
                        responsive: true,
                        plugins: {
                            legend: {
                                position: 'top',
                            },
                            tooltip: {
                                callbacks: {
                                    label: function(tooltipItem) {
                                        return tooltipItem.label + ': ' + tooltipItem.raw + ' tasks';
                                    }
                                }
                            }
                        },
                        scales: {
                            x: {
                                title: {
                                    display: true,
                                    text: 'Month'
                                }
                            },
                            y: {
                                title: {
                                    display: true,
                                    text: 'Number of Tasks'
                                }
                            }
                        }
                    }
                });
            }

            // Create charts on page load
            createCharts();
        </script>
        <a href="chart.jsp" class="back-button">Back to Charts</a>
    </div>
</body>
</html>
