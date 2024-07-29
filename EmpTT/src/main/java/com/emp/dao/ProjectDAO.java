package com.emp.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.emp.model.Project;
import com.emp.util.DBConnection;

public class ProjectDAO {
    private Connection connection;

    public ProjectDAO() {
        connection = DBConnection.getConnection();
    }

    // Method to add a new project
    public void addProject(Project project) throws SQLException {
        String query = "INSERT INTO Projects (projectName, projectDescription) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, project.getProjectName());
            preparedStatement.setString(2, project.getProjectDescription());
            preparedStatement.executeUpdate();
        }
    }

    // Method to update an existing project
    public void updateProject(Project project) throws SQLException {
        String query = "UPDATE Projects SET projectName=?, projectDescription=? WHERE projectId=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, project.getProjectName());
            preparedStatement.setString(2, project.getProjectDescription());
            preparedStatement.setInt(3, project.getProjectId());
            preparedStatement.executeUpdate();
        }
    }

    // Method to delete a project by projectId
    public void deleteProject(int projectId) throws SQLException {
        String query = "DELETE FROM Projects WHERE projectId=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, projectId);
            preparedStatement.executeUpdate();
        }
    }

    // Method to get a project by projectId
    public Project getProjectById(int projectId) throws SQLException {
        String query = "SELECT * FROM Projects WHERE projectId=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, projectId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return new Project(
                    rs.getInt("projectId"),
                    rs.getString("projectName"),
                    rs.getString("projectDescription")
                );
            }
        }
        return null;
    }

    // Method to get all projects
    public List<Project> getAllProjects() throws SQLException {
        List<Project> projects = new ArrayList<>();
        String query = "SELECT * FROM Projects";
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                projects.add(new Project(
                    rs.getInt("projectId"),
                    rs.getString("projectName"),
                    rs.getString("projectDescription")
                ));
            }
        }
        return projects;
    }
}
