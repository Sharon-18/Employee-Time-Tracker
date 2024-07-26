package com.emp.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/taskDashboard")
public class TaskDashboardServlet extends HttpServlet {

   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward to the task dashboard JSP
        request.getRequestDispatcher("taskDashboard.jsp").forward(request, response);
    }
}
