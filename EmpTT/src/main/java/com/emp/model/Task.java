package com.emp.model;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class Task {
    private int taskId;
    private int userId;
    private int projectId;
    private Date taskDate;
    private Time startTime;
    private Time endTime;
    private String taskCategory;
    private String description;
    private double duration;

    // No-argument constructor
    public Task() {}

    // Constructor with all fields
    public Task(int taskId, int userId, int projectId, Date taskDate, Time startTime, Time endTime, String taskCategory, String description, double duration) {
        this.taskId = taskId;
        this.userId = userId;
        this.projectId = projectId;
        this.taskDate = taskDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.taskCategory = taskCategory;
        this.description = description;
        setDuration(duration); // Use setter to validate duration
    }

    // Constructor without taskId (for new tasks)
    public Task(int userId, int projectId, Date taskDate, Time startTime, Time endTime, String taskCategory, String description, double duration) {
        this.userId = userId;
        this.projectId = projectId;
        this.taskDate = taskDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.taskCategory = taskCategory;
        this.description = description;
        setDuration(duration); // Use setter to validate duration
    }

    // Getters and Setters
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

    public Date getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getTaskCategory() {
        return taskCategory;
    }

    public void setTaskCategory(String taskCategory) {
        this.taskCategory = taskCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        if (duration < 0 || duration > 8) {
            throw new IllegalArgumentException("Duration must be between 0 and 8 hours.");
        }
        this.duration = duration;
    }

    @Override
    public String toString() {
        return String.format("Task[ID=%d, UserID=%d, ProjectID=%d, Date=%s, StartTime=%s, EndTime=%s, Category='%s', Description='%s', Duration=%.2f]",
                taskId, userId, projectId, taskDate, startTime, endTime, taskCategory, description, duration);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return taskId == task.taskId &&
                userId == task.userId &&
                projectId == task.projectId &&
                Double.compare(task.duration, duration) == 0 &&
                Objects.equals(taskDate, task.taskDate) &&
                Objects.equals(startTime, task.startTime) &&
                Objects.equals(endTime, task.endTime) &&
                Objects.equals(taskCategory, task.taskCategory) &&
                Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, userId, projectId, taskDate, startTime, endTime, taskCategory, description, duration);
    }
}
