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

@WebServlet("/updatePassword")
public class UpdatePasswordServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        User user = (User) session.getAttribute("user");
        String newPassword = request.getParameter("newPassword");
        
        try {
            user.setPassword(newPassword);
            userDAO.updateUser(user); // Assuming updateUser method exists in UserDAO
            response.sendRedirect("success.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
