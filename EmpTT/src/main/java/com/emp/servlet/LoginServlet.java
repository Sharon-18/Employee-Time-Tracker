package com.emp.servlet;

import java.io.IOException;
import java.sql.SQLException;

import com.emp.dao.UserDAO;
import com.emp.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            request.setAttribute("errorMessage", "null null");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        try {
            User user = userDAO.getUserByUsername(username);
            if (user != null && user.getPassword().equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                if ("Admin".equals(user.getRole())) {
                    response.sendRedirect("adminDashboard.jsp");
                } else {
                    response.sendRedirect("associateDashboard.jsp");
                }
            } else {
                request.setAttribute("errorMessage", "Invalid credentials");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
