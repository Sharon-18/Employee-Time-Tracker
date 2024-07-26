package com.emp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.emp.model.User;
import com.emp.util.DbUtil;

public class UserDAO {

    public User getUserByUsername(String username) throws SQLException {
        User user = null;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE username = ?")) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    user.setRole(resultSet.getString("role"));
                }
            }
        }
        return user;
    }

    public void addUser(User user) throws SQLException {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Users (username, password, role) VALUES (?, ?, ?)")) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole());
            statement.executeUpdate();
        }
    }

    public void updateUser(User user) throws SQLException {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE Users SET username = ?, password = ?, role = ? WHERE id = ?")) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole());
            statement.setInt(4, user.getId());
            statement.executeUpdate();
        }
    }

    public void deleteUser(int id) throws SQLException {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM Users WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
