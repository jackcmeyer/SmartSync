package com.smartsync.model;

import com.smartsync.dto.HouseholdTodoListDTO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The household to do list
 */
@Entity
public class HouseholdTodoList implements ITodoList {

    /**
     * The to do List todoListIdf
     */
    @GeneratedValue
    @Id
    @Column(name = "todo_list_id", unique = true, nullable = false)
    private Long todoListId;

    /**
     * The owner of the to do list todoListId
     */
    private Long householdId;

    /**
     * The name of the to do list
     */
    private String name;

    /**
     * The set of tasks to accomplish
     */
    @OneToMany(targetEntity = HouseholdTodoTask.class, mappedBy = "todoList", fetch = FetchType.EAGER)
    private Set<HouseholdTodoTask> tasks = new HashSet<>();

    /**
     * Initializes a new to do list with an pre filled tasks
     * @param userId the user todoListId
     * @param name the name of the to do list
     * @param tasks the tasks list
     */
    public HouseholdTodoList(Long userId, String name, Set<HouseholdTodoTask> tasks) {
        this.householdId = userId;
        this.name = name;
        this.tasks = tasks;
    }

    public HouseholdTodoList(HouseholdTodoListDTO householdTodoListDTO) {
        this.householdId = householdTodoListDTO.getHouseholdId();
        this.name = householdTodoListDTO.getName();
        this.tasks = new HashSet<>();
    }

    public HouseholdTodoList() {
        // default constructor
    }


    /**
     * Initializes a new to do list with an empty task list
     * @param userId the user todoListId
     * @param name the name of the to do list
     */
    public HouseholdTodoList(Long userId, String name) {
        this.householdId = userId;
        this.name = name;
        this.tasks = new HashSet<>();
    }

    public Long getTodoListId() {
        return todoListId;
    }

    public void setTodoListId(Long todoListId) {
        this.todoListId = todoListId;
    }

    public Long getHouseholdId() {
        return householdId;
    }

    public void setHouseholdId(Long householdId) {
        this.householdId = householdId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<HouseholdTodoTask> getTasks() {
        return tasks;
    }

    public void setTasks(Set<HouseholdTodoTask> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "TodoList{" +
                "todoListId=" + todoListId +
                ", householdId=" + householdId +
                ", name='" + name + '\'' +
                ", tasks=" + tasks +
                '}';
    }

}
