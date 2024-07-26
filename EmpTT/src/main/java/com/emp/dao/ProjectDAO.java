package com.emp.dao;

import java.sql.*;

import com.emp.model.Project;
import com.emp.util.DbUtil;

public class ProjectDAO {
    public Project getProjectById(int id) throws SQLException {
        Project project = null;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Projects WHERE id = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                project = new Project();
                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getString("name"));
                project.setDescription(resultSet.getString("description"));
            }
        }
        return project;
    }

    public void addProject(Project project) throws SQLException {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Projects (name, description) VALUES (?, ?)")) {
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.executeUpdate();
        }
    }

    // Other methods for update and delete can be added similarly
}
