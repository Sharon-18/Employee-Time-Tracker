package com.emp.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import com.emp.dao.TaskDAO;
import com.emp.model.Task;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/taskManager")
public class TaskManagerServlet extends HttpServlet {
    private TaskDAO taskDAO = new TaskDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form parameters
        String taskIdStr = request.getParameter("id"); // Assuming taskId is present for updates
        String userIdStr = request.getParameter("employeeId");
        String projectIdStr = request.getParameter("projectId");
        String dateStr = request.getParameter("date");
        String startTimeStr = request.getParameter("startTime");
        String endTimeStr = request.getParameter("endTime");
        String taskCategory = request.getParameter("taskCategory");
        String description = request.getParameter("description");

        // Initialize variables
        Integer taskId = taskIdStr != null && !taskIdStr.isEmpty() ? Integer.parseInt(taskIdStr) : null;
        Integer userId = null;
        Integer projectId = null;
        LocalDate date = null;
        LocalTime startTime = null;
        LocalTime endTime = null;

        // Validate and parse inputs
        try {
            if (userIdStr != null && !userIdStr.isEmpty()) {
                userId = Integer.parseInt(userIdStr);
            } else {
                throw new IllegalArgumentException("Employee ID is required.");
            }

            if (projectIdStr != null && !projectIdStr.isEmpty()) {
                projectId = Integer.parseInt(projectIdStr);
            } else {
                throw new IllegalArgumentException("Project ID is required.");
            }

            if (dateStr != null && !dateStr.isEmpty()) {
                date = LocalDate.parse(dateStr);
            } else {
                throw new IllegalArgumentException("Date is required.");
            }

            if (startTimeStr != null && !startTimeStr.isEmpty()) {
                startTime = LocalTime.parse(startTimeStr);
            } else {
                throw new IllegalArgumentException("Start time is required.");
            }

            if (endTimeStr != null && !endTimeStr.isEmpty()) {
                endTime = LocalTime.parse(endTimeStr);
            } else {
                throw new IllegalArgumentException("End time is required.");
            }
        } catch (DateTimeParseException | IllegalArgumentException e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("taskForm.jsp").forward(request, response);
            return;
        }

        // Create task object
        Task task = new Task();
        if (taskId != null) {
            task.setTaskId(taskId);
        }
        task.setUserId(userId);
        task.setProjectId(projectId);
        task.setDate(date);
        task.setStartTime(startTime);
        task.setEndTime(endTime);
        task.setCategory(taskCategory);
        task.setDescription(description);

        // Add or update task in database
        try {
            if (taskId == null) {
                taskDAO.addTask(task);
                response.sendRedirect("associateDashboard.jsp?success=Task added successfully");
            } else {
                taskDAO.updateTask(task); // Ensure you have an updateTask method in TaskDAO
                response.sendRedirect("taskList.jsp?success=Task updated successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error processing task");
            request.getRequestDispatcher("taskForm.jsp").forward(request, response);
        }
    }
}
