<%@ page import="java.util.List" %>
<%@ page import="com.emp.model.Task" %>
<!DOCTYPE html>
<html>
<head>
    <title>Task Analysis</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    <h1>Task Analysis</h1>
    
    <!-- Create a canvas element for the chart -->
    <canvas id="taskChart" width="800" height="400"></canvas>
    
    <script>
        document.addEventListener("DOMContentLoaded", function() {
            var ctx = document.getElementById('taskChart').getContext('2d');
            
            var data = {
                labels: [<% 
                    List<Task> tasks = (List<Task>) request.getAttribute("tasks");
                    for (int i = 0; i < tasks.size(); i++) {
                        Task task = tasks.get(i);
                        out.print("'" + task.getProjectId() + "'" + (i < tasks.size() - 1 ? ", " : ""));
                    }
                %>],
                datasets: [{
                    label: 'Task Duration',
                    data: [<% 
                        for (int i = 0; i < tasks.size(); i++) {
                            Task task = tasks.get(i);
                            out.print(task.getEndTime().getHour() - task.getStartTime().getHour() + (i < tasks.size() - 1 ? ", " : ""));
                        }
                    %>],
                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
                    borderColor: 'rgba(75, 192, 192, 1)',
                    borderWidth: 1
                }]
            };
            
            var myChart = new Chart(ctx, {
                type: 'bar',
                data: data,
                options: {
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    }
                }
            });
        });
    </script>
</body>
</html>
