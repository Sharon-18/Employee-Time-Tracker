package com.emp.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.emp.dao.TaskDAO;
import com.emp.model.Task;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/analyzeTask")
public class AnalyzeTaskServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TaskDAO taskDAO = new TaskDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId;
        try {
            userId = Integer.parseInt(request.getParameter("userId"));
        } catch (NumberFormatException e) {
            // Handle invalid userId format
            response.sendRedirect("error.jsp");
            return;
        }

        try {
            // Fetch tasks for analysis
            List<Task> tasks = taskDAO.getTasksByUserId(userId);
            request.setAttribute("tasks", tasks);
            request.getRequestDispatcher("chart.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
