package com.emp.dao;

import com.emp.model.Task;
import com.emp.util.DbUtil;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaskDAO {
    private static final Logger LOGGER = Logger.getLogger(TaskDAO.class.getName());

    // Add a new task
    public void addTask(Task task) throws SQLException {
        String query = "INSERT INTO Tasks (user_id, project_id, date, start_time, end_time, category, description) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, task.getUserId());
            statement.setInt(2, task.getProjectId());
            statement.setDate(3, Date.valueOf(task.getDate()));
            statement.setTime(4, Time.valueOf(task.getStartTime()));
            statement.setTime(5, Time.valueOf(task.getEndTime()));
            statement.setString(6, task.getCategory());
            statement.setString(7, task.getDescription());

            statement.executeUpdate();
            LOGGER.log(Level.INFO, "Task added successfully: {0}", task);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error adding task: {0}", e.getMessage());
            throw e;
        }
    }

    // Get tasks by user ID
    public List<Task> getTasksByUserId(int userId) throws SQLException {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM Tasks WHERE user_id = ?";
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Task task = new Task();
                    task.setTaskId(resultSet.getInt("task_id"));
                    task.setUserId(resultSet.getInt("user_id"));
                    task.setProjectId(resultSet.getInt("project_id"));
                    task.setDate(resultSet.getDate("date").toLocalDate());
                    task.setStartTime(resultSet.getTime("start_time").toLocalTime());
                    task.setEndTime(resultSet.getTime("end_time").toLocalTime());
                    task.setCategory(resultSet.getString("category"));
                    task.setDescription(resultSet.getString("description"));
                    tasks.add(task);
                }
            }
            LOGGER.log(Level.INFO, "Tasks retrieved for user ID: {0}", userId);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving tasks for user ID {0}: {1}", new Object[]{userId, e.getMessage()});
            throw e;
        }
        return tasks;
    }

    // Get a task by its ID
    public Task getTaskById(int id) throws SQLException {
        Task task = null;
        String query = "SELECT * FROM Tasks WHERE task_id = ?";
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    task = new Task();
                    task.setTaskId(resultSet.getInt("task_id"));
                    task.setUserId(resultSet.getInt("user_id"));
                    task.setProjectId(resultSet.getInt("project_id"));
                    task.setDate(resultSet.getDate("date").toLocalDate());
                    task.setStartTime(resultSet.getTime("start_time").toLocalTime());
                    task.setEndTime(resultSet.getTime("end_time").toLocalTime());
                    task.setCategory(resultSet.getString("category"));
                    task.setDescription(resultSet.getString("description"));
                }
            }
            LOGGER.log(Level.INFO, "Task retrieved with ID: {0}", id);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving task with ID {0}: {1}", new Object[]{id, e.getMessage()});
            throw e;
        }
        return task;
    }

    // Update an existing task
    public void updateTask(Task task) throws SQLException {
        String query = "UPDATE Tasks SET user_id = ?, project_id = ?, date = ?, start_time = ?, end_time = ?, category = ?, description = ? WHERE task_id = ?";
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, task.getUserId());
            statement.setInt(2, task.getProjectId());
            statement.setDate(3, Date.valueOf(task.getDate()));
            statement.setTime(4, Time.valueOf(task.getStartTime()));
            statement.setTime(5, Time.valueOf(task.getEndTime()));
            statement.setString(6, task.getCategory());
            statement.setString(7, task.getDescription());
            statement.setInt(8, task.getTaskId());

            statement.executeUpdate();
            LOGGER.log(Level.INFO, "Task updated successfully: {0}", task);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating task: {0}", e.getMessage());
            throw e;
        }
    }

    // Delete a task by its ID
    public void deleteTask(int id) throws SQLException {
        String query = "DELETE FROM Tasks WHERE task_id = ?";
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            statement.executeUpdate();
            LOGGER.log(Level.INFO, "Task deleted with ID: {0}", id);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting task with ID {0}: {1}", new Object[]{id, e.getMessage()});
            throw e;
        }
    }
}
