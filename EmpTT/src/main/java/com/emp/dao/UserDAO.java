package com.emp.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.emp.model.User;
import com.emp.util.DBConnection;

public class UserDAO {
    private Connection connection;

    public UserDAO() {
        connection = DBConnection.getConnection();
    }

    // Method to add a new user
    public void addUser(User user) throws SQLException {
        String query = "INSERT INTO Users (username, password, role, fullName) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword()); // Store password as plain text
            preparedStatement.setString(3, user.getRole());
            preparedStatement.setString(4, user.getFullName());
            preparedStatement.executeUpdate();
        }
    }

    // Method to update an existing user
    public void updateUser(User user) throws SQLException {
        String query = "UPDATE Users SET username=?, password=?, role=?, fullName=? WHERE userId=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword()); // Store password as plain text
            preparedStatement.setString(3, user.getRole());
            preparedStatement.setString(4, user.getFullName());
            preparedStatement.setInt(5, user.getUserId());
            preparedStatement.executeUpdate();
        }
    }

    // Method to delete a user by userId
    public void deleteUser(int userId) throws SQLException {
        String query = "DELETE FROM Users WHERE userId=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
        }
    }

    // Method to get a user by userId
    public User getUserById(int userId) throws SQLException {
        String query = "SELECT * FROM Users WHERE userId=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return new User(
                    rs.getInt("userId"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("role"),
                    rs.getString("fullName")
                );
            }
        }
        return null;
    }

    // Method to get all users
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM Users";
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                users.add(new User(
                    rs.getInt("userId"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("role"),
                    rs.getString("fullName")
                ));
            }
        }
        return users;
    }

    // Method to authenticate a user
    public User authenticate(String username, String password) throws SQLException {
        String query = "SELECT * FROM Users WHERE username=? AND password=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password); // Compare plain text passwords
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return new User(
                    rs.getInt("userId"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("role"),
                    rs.getString("fullName")
                );
            }
        }
        return null;
    }

    // Method to update a user's password
    public void updatePassword(int userId, String oldPassword, String newPassword) throws SQLException {
        // Verify that the old password is correct
        String verifyQuery = "SELECT * FROM Users WHERE userId=? AND password=?";
        try (PreparedStatement verifyStatement = connection.prepareStatement(verifyQuery)) {
            verifyStatement.setInt(1, userId);
            verifyStatement.setString(2, oldPassword); // Compare plain text passwords
            ResultSet rs = verifyStatement.executeQuery();
            if (!rs.next()) {
                throw new SQLException("Old password is incorrect");
            }
        }

        // Update the user's password
        String updateQuery = "UPDATE Users SET password=? WHERE userId=?";
        try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
            updateStatement.setString(1, newPassword); // Store new password as plain text
            updateStatement.setInt(2, userId);
            updateStatement.executeUpdate();
        }
    }
}
