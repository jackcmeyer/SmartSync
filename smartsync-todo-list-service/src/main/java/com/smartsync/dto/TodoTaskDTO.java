package com.smartsync.dto;

import java.util.Date;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The to do task dto
 */
public class TodoTaskDTO {

    /**
     * The id of the to do list to add the task to
     */
    private Long todoListId;

    /**
     * The description of the task
     */
    private String description;

    /**
     * The due date for the task
     */
    private Date dueDate;

    public TodoTaskDTO(Long todoListId, String description, Date dueDate) {
        this.todoListId = todoListId;
        this.description = description;
        this.dueDate = dueDate;
    }

    public TodoTaskDTO() {
    }

    public Long getTodoListId() {
        return todoListId;
    }

    public void setTodoListId(Long todoListId) {
        this.todoListId = todoListId;
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
}
