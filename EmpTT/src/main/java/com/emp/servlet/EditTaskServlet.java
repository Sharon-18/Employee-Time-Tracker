package com.emp.servlet;

import com.emp.dao.TaskDAO;
import com.emp.model.Task;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

@WebServlet("/EditTaskServlet")
public class EditTaskServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve form parameters
            int taskId = Integer.parseInt(request.getParameter("taskId"));
            int userId = Integer.parseInt(request.getParameter("userId"));
            int projectId = Integer.parseInt(request.getParameter("projectId"));
            Date taskDate = Date.valueOf(request.getParameter("taskDate"));
            Time startTime = Time.valueOf(request.getParameter("startTime") + ":00"); // Appending seconds
            Time endTime = Time.valueOf(request.getParameter("endTime") + ":00"); // Appending seconds
            String taskCategory = request.getParameter("taskCategory");
            String description = request.getParameter("description");
            double duration = Double.parseDouble(request.getParameter("duration"));

            // Create a Task object with the updated values
            Task task = new Task(taskId, userId, projectId, taskDate, startTime, endTime, taskCategory, description, duration);

            // Create TaskDAO object to update the task in the database
            TaskDAO taskDAO = new TaskDAO();
            boolean success = taskDAO.updateTask(task);

            // Redirect to appropriate page based on success or failure
            if (success) {
                response.sendRedirect("taskList.jsp"); // Redirect to task list page on success
            } else {
                request.setAttribute("errorMessage", "Error updating task. Please try again.");
                request.getRequestDispatcher("errorPage.jsp").forward(request, response); // Forward to error page on failure
            }
        } catch (Exception e) {
            // Handle exceptions
            request.setAttribute("errorMessage", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
    }
}
