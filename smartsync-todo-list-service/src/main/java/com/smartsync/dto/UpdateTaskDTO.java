package com.smartsync.dto;

import java.util.Date;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 */
public class UpdateTaskDTO {

    /**
     * The task id
     */
    private Long taskId;

    /**
     * The description
     */
    private String description;

    /**
     * Is completed falg
     */
    private boolean isCompleted;

    /**
     * The due date
     */
    private Date dueDate;

    public UpdateTaskDTO(Long taskId, String description, Date dueDate, boolean isCompleted) {
        this.taskId = taskId;
        this.description = description;
        this.dueDate = dueDate;
        this.isCompleted = isCompleted;
    }

    public UpdateTaskDTO() {}


    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        return "UpdateTaskDTO{" +
                "taskId=" + taskId +
                ", description='" + description + '\'' +
                ", isCompleted=" + isCompleted +
                ", dueDate=" + dueDate +
                '}';
    }
}
