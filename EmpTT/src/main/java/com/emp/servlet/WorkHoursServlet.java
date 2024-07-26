package com.emp.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import com.emp.dao.TaskDAO;
import com.emp.model.Task;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/workHours")
public class WorkHoursServlet extends HttpServlet {
    private TaskDAO taskDAO = new TaskDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));

        try {
            // Fetch tasks for the employee
            List<Task> tasks = taskDAO.getTasksByUserId(employeeId);  // Updated to use the correct method

            // Calculate total work hours
            double totalHours = 0;
            for (Task task : tasks) {
                // Assuming startTime and endTime are LocalTime objects
                LocalDateTime startTime = LocalDateTime.of(task.getDate(), task.getStartTime());
                LocalDateTime endTime = LocalDateTime.of(task.getDate(), task.getEndTime());

                // Calculate the duration in hours
                Duration duration = Duration.between(startTime, endTime);
                totalHours += duration.toHours() + (double) duration.toMinutesPart() / 60;
            }

            // Pass total work hours to JSP
            request.setAttribute("totalHours", totalHours);
            request.getRequestDispatcher("workHours.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
