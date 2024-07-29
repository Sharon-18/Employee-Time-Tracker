package com.emp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DailyTasksServlet")
public class DailyTasksServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/yourdatabase";
    private static final String DB_USER = "username";
    private static final String DB_PASSWORD = "password";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        List<String> labels = new ArrayList<>();
        List<Double> values = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement("SELECT task_category, SUM(duration) AS total_hours FROM tasks WHERE task_date = CURDATE() GROUP BY task_category");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                labels.add(rs.getString("task_category"));
                values.add(rs.getDouble("total_hours"));
            }

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JSONObject errorResponse = new JSONObject();
            errorResponse.put("error", "Database access error");
            response.getWriter().write(errorResponse.toString());
            e.printStackTrace();
            return;
        }

        // Convert lists to JSON
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("labels", new JSONArray(labels));
        jsonResponse.put("values", new JSONArray(values));

        // Write JSON response
        try (PrintWriter out = response.getWriter()) {
            out.print(jsonResponse.toString());
            out.flush();
        }
    }
}
