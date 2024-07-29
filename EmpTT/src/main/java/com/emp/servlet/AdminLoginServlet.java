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

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            response.sendRedirect("adminLogin.jsp?error=Username and password are required");
            return;
        }

        try {
            User user = userDAO.authenticate(username, password);
            if (user != null && "Admin".equalsIgnoreCase(user.getRole())) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect("adminDashboard.jsp");
            } else {
                response.sendRedirect("adminLogin.jsp?error=Invalid username or password");
            }
        } catch (SQLException e) {
            throw new ServletException("Database access error", e);
        }
    }
}
