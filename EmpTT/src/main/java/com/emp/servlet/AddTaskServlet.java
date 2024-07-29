package com.emp.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.emp.dao.TaskDAO;
import com.emp.model.Task;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AddTaskServlet")
public class AddTaskServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TaskDAO taskDAO;
    private static final Logger logger = Logger.getLogger(AddTaskServlet.class.getName());

    @Override
    public void init() throws ServletException {
        taskDAO = new TaskDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve and validate parameters from request
            String userIdStr = request.getParameter("userId");
            String projectIdStr = request.getParameter("projectId");
            String taskDateStr = request.getParameter("taskDate");
            String startTimeStr = request.getParameter("startTime");
            String endTimeStr = request.getParameter("endTime");
            String taskCategory = request.getParameter("taskCategory");
            String description = request.getParameter("description");
            String durationStr = request.getParameter("duration");

            if (areParametersInvalid(userIdStr, projectIdStr, taskDateStr, startTimeStr, endTimeStr, taskCategory, description, durationStr)) {
                request.setAttribute("errorMessage", "All fields are required.");
                request.getRequestDispatcher("addTask.jsp").forward(request, response);
                return;
            }

            // Parse and validate input
            int userId = parseInteger(userIdStr, "User ID");
            int projectId = parseInteger(projectIdStr, "Project ID");
            Date taskDate = parseDate(taskDateStr);
            Time startTime = parseTime(startTimeStr);
            Time endTime = parseTime(endTimeStr);
            validateTimes(startTime, endTime);

            double duration = parseDouble(durationStr, "Duration");
            validateDuration(duration);

            // Create Task object and add to database
            Task task = new Task(userId, projectId, taskDate, startTime, endTime, taskCategory, description, duration);
            taskDAO.addTask(task);

            // Redirect to task list page
            response.sendRedirect("taskList.jsp");

        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, "Invalid number format", e);
            request.setAttribute("errorMessage", "Invalid number format.");
            request.getRequestDispatcher("addTask.jsp").forward(request, response);
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, "Illegal argument", e);
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("addTask.jsp").forward(request, response);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error", e);
            request.setAttribute("errorMessage", "An error occurred while adding the task. Please try again.");
            request.getRequestDispatcher("addTask.jsp").forward(request, response);
        }
    }

    private boolean areParametersInvalid(String... params) {
        for (String param : params) {
            if (param == null || param.trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private void validateTimes(Time startTime, Time endTime) throws IllegalArgumentException {
        if (endTime.before(startTime)) {
            throw new IllegalArgumentException("End time cannot be before start time.");
        }
    }

    private void validateDuration(double duration) throws IllegalArgumentException {
        if (duration < 0 || duration > 8) {
            throw new IllegalArgumentException("Duration must be between 0 and 8 hours.");
        }
    }

    private int parseInteger(String valueStr, String fieldName) throws IllegalArgumentException {
        try {
            return Integer.parseInt(valueStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " must be a valid integer.");
        }
    }

    private double parseDouble(String valueStr, String fieldName) throws IllegalArgumentException {
        try {
            return Double.parseDouble(valueStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " must be a valid number.");
        }
    }

    private Date parseDate(String dateStr) throws IllegalArgumentException {
        try {
            return Date.valueOf(dateStr);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid date format. Expected format is YYYY-MM-DD.");
        }
    }

    private Time parseTime(String timeStr) throws IllegalArgumentException {
        if (timeStr == null || timeStr.trim().isEmpty()) {
            throw new IllegalArgumentException("Time cannot be null or empty.");
        }

        String timePattern = "^([01]\\d|2[0-3]):([0-5]\\d)$"; // Pattern for HH:MM

        if (!timeStr.matches(timePattern)) {
            throw new IllegalArgumentException("Invalid time format. Expected format is HH:MM.");
        }

        try {
            return Time.valueOf(timeStr + ":00"); // Append seconds to fit the Time format
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid time format. Expected format is HH:MM.");
        }
    }
}
