package com.emp.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.emp.dao.TaskDAO;

@WebServlet("/MonthlyTasksServlet")
public class MonthlyTasksServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TaskDAO taskDAO;

    public void init() {
        taskDAO = new TaskDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId;
        try {
            userId = Integer.parseInt(request.getParameter("userId")); // Get userId from request
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\":\"Invalid user ID format\"}");
            return;
        }

        try {
            List<String> labels = taskDAO.getMonthlyLabels(userId);
            List<Double> data = taskDAO.getMonthlyData(userId);

            // Construct JSON response
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            StringBuilder jsonBuilder = new StringBuilder();
            jsonBuilder.append("{");

            jsonBuilder.append("\"labels\":[");
            for (int i = 0; i < labels.size(); i++) {
                jsonBuilder.append("\"").append(labels.get(i)).append("\"");
                if (i < labels.size() - 1) {
                    jsonBuilder.append(",");
                }
            }
            jsonBuilder.append("],");

            jsonBuilder.append("\"values\":[");
            for (int i = 0; i < data.size(); i++) {
                jsonBuilder.append(data.get(i));
                if (i < data.size() - 1) {
                    jsonBuilder.append(",");
                }
            }
            jsonBuilder.append("]");

            jsonBuilder.append("}");

            // Write JSON to response
            response.getWriter().write(jsonBuilder.toString());

        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\":\"Database access error\"}");
            e.printStackTrace(); // Log the exception for debugging
        }
    }
}
