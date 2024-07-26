<%@ page import="java.util.List" %>
<%@ page import="com.emp.dao.TaskDAO" %>
<%@ page import="com.emp.model.Task" %>
<%@ page import="com.emp.model.User" %>
<%@ page session="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    
    TaskDAO taskDAO = new TaskDAO();
    List<Task> tasks = taskDAO.getTasksByUserId(user.getId());
    String successMessage = request.getParameter("success");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Associate Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            color: #333;
            margin: 0;
            padding: 0;
        }

        header {
            background-color: #e91e63;
            color: white;
            padding: 10px 20px;
            text-align: center;
        }

        h1, h2 {
            color: #e91e63;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            background-color: white;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            padding: 12px;
            text-align: left;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        .success {
            color: green;
            margin: 20px;
        }

        a {
            color: #e91e63;
            text-decoration: none;
            margin: 20px;
            display: inline-block;
        }

        .container {
            width: 80%;
            margin: auto;
            overflow: hidden;
        }

        .chart-container {
            margin: 20px 0;
        }
    </style>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    <header>
        <h1>Welcome, <%= user.getUsername() %></h1>
    </header>

    <div class="container">
        <% if (successMessage != null) { %>
            <p class="success"><%= successMessage %></p>
        <% } %>

        <h2>Your Tasks</h2>
        <table>
            <thead>
                <tr>
                    <th>Project</th>
                    <th>Date</th>
                    <th>Start Time</th>
                    <th>End Time</th>
                    <th>Category</th>
                    <th>Description</th>
                </tr>
            </thead>
            <tbody>
                <% for (Task task : tasks) { %>
                    <tr>
                        <td><%= task.getProjectId() %></td>
                        <td><%= task.getDate() %></td>
                        <td><%= task.getStartTime() %></td>
                        <td><%= task.getEndTime() %></td>
                        <td><%= task.getCategory() %></td>
                        <td><%= task.getDescription() %></td>
                    </tr>
                <% } %>
            </tbody>
        </table>

        <a href="addTask.jsp">Add New Task</a>

        <h2>Charts</h2>
        <div class="chart-container">
            <canvas id="taskChart"></canvas>
        </div>
    </div>

    <script>
        // Prepare data for the chart
        const ctx = document.getElementById('taskChart').getContext('2d');

        const labels = <%= tasks.stream().map(task -> "'" + task.getDate() + "'").toList() %>;
        const data = <%= tasks.stream().map(task -> {
            int duration = task.getEndTime().getHour() - task.getStartTime().getHour();
            return duration;
        }).toList() %>;

        const taskChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: labels,
                datasets: [{
                    label: 'Hours Worked',
                    data: data,
                    backgroundColor: '#e91e63',
                    borderColor: '#c2185b',
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true,
                        title: {
                            display: true,
                            text: 'Hours'
                        }
                    },
                    x: {
                        title: {
                            display: true,
                            text: 'Date'
                        }
                    }
                }
            }
        });
    </script>
</body>
</html>
