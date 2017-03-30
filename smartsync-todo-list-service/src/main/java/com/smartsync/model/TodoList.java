package com.smartsync.model;

import com.smartsync.dto.TodoListDTO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The TODO list entity
 */
@Entity
public class TodoList implements ITodoList {

    /**
     * The to do List todoListId
     */
    @GeneratedValue @Id
    @Column(name = "todo_list_id", unique = true, nullable = false)
    private Long todoListId;

    /**
     * The owner of the to do list todoListId
     */
    private Long userId;

    /**
     * The name of the to do list
     */
    private String name;

    /**
     * The set of tasks to accomplish
     */
    @OneToMany(targetEntity = TodoTask.class, mappedBy = "todoList", fetch = FetchType.EAGER)
    private Set<TodoTask> tasks = new HashSet<>();

    /**
     * Initializes a new to do list with an pre filled tasks
     * @param userId the user todoListId
     * @param name the name of the to do list
     * @param tasks the tasks list
     */
    public TodoList(Long userId, String name, Set<TodoTask> tasks) {
        this.userId = userId;
        this.name = name;
        this.tasks = tasks;
    }

    public TodoList(TodoListDTO todoListDTO) {
        this.userId = todoListDTO.getUserId();
        this.name = todoListDTO.getName();
        this.tasks = new HashSet<>();
    }

    public TodoList() {
        // default constructor
    }


    /**
     * Initializes a new to do list with an empty task list
     * @param userId the user todoListId
     * @param name the name of the to do list
     */
    public TodoList(Long userId, String name) {
        this.userId = userId;
        this.name = name;
        this.tasks = new HashSet<>();
    }

    public Long getTodoListId() {
        return todoListId;
    }

    public void setTodoListId(Long todoListId) {
        this.todoListId = todoListId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<TodoTask> getTasks() {
        return tasks;
    }

    public void setTasks(Set<TodoTask> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "TodoList{" +
                "todoListId=" + todoListId +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", tasks=" + tasks +
                '}';
    }
}
