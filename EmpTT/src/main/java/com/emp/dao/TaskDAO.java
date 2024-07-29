package com.emp.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.emp.model.Task;
import com.emp.util.DBConnection;

public class TaskDAO {
    private Connection connection;
    private static final Logger logger = Logger.getLogger(TaskDAO.class.getName());

    public TaskDAO() {
        connection = DBConnection.getConnection();
    }

    public void addTask(Task task) throws SQLException {
        String query = "INSERT INTO Tasks (user_id, project_id, task_date, start_time, end_time, task_category, description, duration) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, task.getUserId());
            preparedStatement.setInt(2, task.getProjectId());
            preparedStatement.setDate(3, task.getTaskDate());
            preparedStatement.setTime(4, task.getStartTime());
            preparedStatement.setTime(5, task.getEndTime());
            preparedStatement.setString(6, task.getTaskCategory());
            preparedStatement.setString(7, task.getDescription());
            preparedStatement.setDouble(8, task.getDuration());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error adding task", e);
            throw e; // Re-throw to let the caller handle it
        }
    }

    public boolean updateTask(Task task) throws SQLException {
        String query = "UPDATE Tasks SET project_id=?, task_date=?, start_time=?, end_time=?, task_category=?, description=?, duration=? WHERE task_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, task.getProjectId());
            preparedStatement.setDate(2, task.getTaskDate());
            preparedStatement.setTime(3, task.getStartTime());
            preparedStatement.setTime(4, task.getEndTime());
            preparedStatement.setString(5, task.getTaskCategory());
            preparedStatement.setString(6, task.getDescription());
            preparedStatement.setDouble(7, task.getDuration());
            preparedStatement.setInt(8, task.getTaskId());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating task", e);
            throw e; // Re-throw to let the caller handle it
        }
    }

    public boolean deleteTask(int taskId) throws SQLException {
        String query = "DELETE FROM Tasks WHERE task_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, taskId);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting task", e);
            throw e; // Re-throw to let the caller handle it
        }
    }

    public Task getTaskById(int taskId) throws SQLException {
        String query = "SELECT * FROM Tasks WHERE task_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, taskId);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return new Task(
                        rs.getInt("task_id"),
                        rs.getInt("user_id"),
                        rs.getInt("project_id"),
                        rs.getDate("task_date"),
                        rs.getTime("start_time"),
                        rs.getTime("end_time"),
                        rs.getString("task_category"),
                        rs.getString("description"),
                        rs.getDouble("duration")
                    );
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving task by ID", e);
            throw e; // Re-throw to let the caller handle it
        }
        return null;
    }

    public List<Task> getAllTasks() throws SQLException {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM Tasks";
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                tasks.add(new Task(
                    rs.getInt("task_id"),
                    rs.getInt("user_id"),
                    rs.getInt("project_id"),
                    rs.getDate("task_date"),
                    rs.getTime("start_time"),
                    rs.getTime("end_time"),
                    rs.getString("task_category"),
                    rs.getString("description"),
                    rs.getDouble("duration")
                ));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving all tasks", e);
            throw e; // Re-throw to let the caller handle it
        }
        return tasks;
    }

    public List<Task> getTasksByUserId(int userId) throws SQLException {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM Tasks WHERE user_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    tasks.add(new Task(
                        rs.getInt("task_id"),
                        rs.getInt("user_id"),
                        rs.getInt("project_id"),
                        rs.getDate("task_date"),
                        rs.getTime("start_time"),
                        rs.getTime("end_time"),
                        rs.getString("task_category"),
                        rs.getString("description"),
                        rs.getDouble("duration")
                    ));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving tasks by user ID", e);
            throw e; // Re-throw to let the caller handle it
        }
        return tasks;
    }

    public List<Double> getWeeklyData(int userId) throws SQLException {
        List<Double> data = new ArrayList<>();
        String query = "SELECT WEEK(task_date) AS week, SUM(duration) AS totalDuration " +
                       "FROM Tasks WHERE user_id=? AND YEAR(task_date) = YEAR(CURDATE()) " +
                       "GROUP BY week ORDER BY week";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    data.add(rs.getDouble("totalDuration"));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving weekly data", e);
            throw e; // Re-throw to let the caller handle it
        }
        return data;
    }

    public List<String> getWeeklyLabels(int userId) throws SQLException {
        List<String> labels = new ArrayList<>();
        String query = "SELECT DISTINCT WEEK(task_date) AS week " +
                       "FROM Tasks WHERE user_id=? AND YEAR(task_date) = YEAR(CURDATE()) " +
                       "ORDER BY week";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    labels.add("Week " + rs.getInt("week"));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving weekly labels", e);
            throw e; // Re-throw to let the caller handle it
        }
        return labels;
    }

    public List<String> getMonthlyLabels(int userId) throws SQLException {
        List<String> labels = new ArrayList<>();
        String query = "SELECT DISTINCT DATE_FORMAT(task_date, '%Y-%m') AS month " +
                       "FROM Tasks WHERE user_id=? AND YEAR(task_date) = YEAR(CURDATE()) " +
                       "ORDER BY month";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    labels.add(rs.getString("month"));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving monthly labels", e);
            throw e; // Re-throw to let the caller handle it
        }
        return labels;
    }

    public List<Double> getMonthlyData(int userId) throws SQLException {
        List<Double> data = new ArrayList<>();
        String query = "SELECT DATE_FORMAT(task_date, '%Y-%m') AS month, SUM(duration) AS totalDuration " +
                       "FROM Tasks WHERE user_id=? AND YEAR(task_date) = YEAR(CURDATE()) " +
                       "GROUP BY month ORDER BY month";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    data.add(rs.getDouble("totalDuration"));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving monthly data", e);
            throw e; // Re-throw to let the caller handle it
        }
        return data;
    }

    public List<String> getDailyLabels(int userId) throws SQLException {
        List<String> labels = new ArrayList<>();
        String query = "SELECT DISTINCT task_category FROM Tasks WHERE user_id=? AND task_date = CURDATE()";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    labels.add(rs.getString("task_category"));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving daily labels", e);
            throw e; // Re-throw to let the caller handle it
        }
        return labels;
    }

    public List<Double> getDailyData(int userId) throws SQLException {
        List<Double> data = new ArrayList<>();
        String query = "SELECT task_category, SUM(duration) AS totalDuration FROM Tasks WHERE user_id=? AND task_date = CURDATE() GROUP BY task_category";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    data.add(rs.getDouble("totalDuration"));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving daily data", e);
            throw e; // Re-throw to let the caller handle it
        }
        return data;
    }
}
