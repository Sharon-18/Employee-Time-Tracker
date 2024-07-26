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

@WebServlet("/pieChart")
public class PieChartServlet extends HttpServlet {
    private TaskDAO taskDAO = new TaskDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int employeeId;
        try {
            employeeId = Integer.parseInt(request.getParameter("employeeId"));
        } catch (NumberFormatException e) {
            response.sendRedirect("error.jsp?error=Invalid employee ID");
            return;
        }

        try {
            // Fetch tasks for the employee and pass data to JSP
            List<Task> tasks = taskDAO.getTasksByUserId(employeeId);
            request.setAttribute("tasks", tasks);
            request.getRequestDispatcher("pieChart.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?error=Error fetching tasks");
        }
    }
}
