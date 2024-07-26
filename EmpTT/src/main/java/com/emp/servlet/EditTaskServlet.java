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

@WebServlet("/editTask")
public class EditTaskServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TaskDAO taskDAO = new TaskDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the task ID from the request
        String taskIdParam = request.getParameter("id");
        if (taskIdParam == null || taskIdParam.isEmpty()) {
            response.sendRedirect("taskList.jsp?error=Task%20ID%20is%20missing");
            return;
        }

        int taskId;
        try {
            taskId = Integer.parseInt(taskIdParam);
        } catch (NumberFormatException e) {
            response.sendRedirect("taskList.jsp?error=Invalid%20Task%20ID");
            return;
        }

        try {
            // Retrieve the task from the database
            Task task = taskDAO.getTaskById(taskId);
            if (task != null) {
                request.setAttribute("task", task);
                request.getRequestDispatcher("editTask.jsp").forward(request, response);
            } else {
                response.sendRedirect("taskList.jsp?error=Task%20not%20found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?error=Database%20error%20while%20retrieving%20task");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form parameters
        int id;
        int employeeId;
        int projectId;
        LocalDate date;
        LocalTime startTime;
        LocalTime endTime;
        String taskCategory;
        String description;

        try {
            id = Integer.parseInt(request.getParameter("id"));
            employeeId = Integer.parseInt(request.getParameter("employeeId"));
            projectId = Integer.parseInt(request.getParameter("projectId"));
            date = LocalDate.parse(request.getParameter("date"));
            startTime = LocalTime.parse(request.getParameter("startTime"));
            endTime = LocalTime.parse(request.getParameter("endTime"));
            taskCategory = request.getParameter("taskCategory");
            description = request.getParameter("description");
        } catch (NumberFormatException | DateTimeParseException e) {
            e.printStackTrace(); // Log the exception for debugging
            response.sendRedirect("editTask.jsp?error=Invalid%20input%20format");
            return;
        }

        // Create a Task object
        Task task = new Task();
        task.setTaskId(id);
        task.setUserId(employeeId);
        task.setProjectId(projectId);
        task.setDate(date);
        task.setStartTime(startTime);
        task.setEndTime(endTime);
        task.setCategory(taskCategory);
        task.setDescription(description);

        try {
            // Update the task in the database
            taskDAO.updateTask(task);
            response.sendRedirect("taskList.jsp?success=Task%20updated%20successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?error=Error%20updating%20task");
        }
    }
}
