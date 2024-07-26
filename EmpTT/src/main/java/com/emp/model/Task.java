package com.emp.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Task {
    private int taskId;         // Matches the task_id column
    private int userId;         // Matches the user_id column
    private int projectId;      // Matches the project_id column
    private LocalDate date;     // Matches the date column
    private LocalTime startTime; // Matches the start_time column
    private LocalTime endTime;   // Matches the end_time column
    private String category;    // Matches the category column
    private String description; // Matches the description column

    // Getters and setters for all fields
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
