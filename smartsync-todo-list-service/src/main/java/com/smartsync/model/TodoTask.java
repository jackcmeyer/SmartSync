package com.smartsync.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smartsync.dto.TodoTaskDTO;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The to do list task
 */
@Entity
public class TodoTask implements ITodoTask {

    /**
     * The to do task todoTaskId
     */
    @GeneratedValue @Id
    @Column(name = "todo_task_id", unique = true, nullable = false)
    private Long todoTaskId;

    /**
     * Description of the task
     */
    private String description;

    /**
     * is completed
     */
    private boolean isCompleted;

    /**
     * The due date for the task
     */
    private Date dueDate;

    /**
     * The to do list the task belongs to
     */
    @ManyToOne(targetEntity = TodoList.class)
    @JsonIgnore
    private TodoList todoList;

    /**
     * The date the task was completed
     */
    private Date created;

    /**
     * The date the task was last updated
     */
    private Date lastUpdated;

    public TodoTask(String description, Date dueDate, TodoList todoList) {
        this.description = description;
        this.isCompleted = false;
        this.dueDate = dueDate;
        this.todoList = todoList;
        this.created =  new Date();
        this.lastUpdated = new Date();
    }

    public TodoTask(TodoTaskDTO todoTaskDTO, TodoList todoList) {
        this.description = todoTaskDTO.getDescription();
        this.isCompleted = false;
        this.dueDate = todoTaskDTO.getDueDate();
        this.todoList = todoList;
        this.created = new Date();
        this.lastUpdated = new Date();
    }

    public TodoTask() {
        // default constructor
    }

    public Long getTodoTaskId() {
        return todoTaskId;
    }

    public void setTodoTaskId(Long todoTaskId) {
        this.todoTaskId = todoTaskId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public TodoList getTodoList() {
        return todoList;
    }

    public void setTodoList(TodoList todoList) {
        this.todoList = todoList;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return "TodoTask{" +
                "todoTaskId=" + todoTaskId +
                ", description='" + description + '\'' +
                ", isCompleted=" + isCompleted +
                ", dueDate=" + dueDate +
                ", created=" + created +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
