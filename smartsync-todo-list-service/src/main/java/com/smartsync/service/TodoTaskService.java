package com.smartsync.service;

import com.smartsync.dto.TodoTaskDTO;
import com.smartsync.dto.UpdateTaskDTO;
import com.smartsync.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The to do task service
 */
@Component
public class TodoTaskService {

    @Autowired
    private TodoTaskRepository todoTaskRepository;

    @Autowired
    private HouseholdTodoTaskRepository householdTodoTaskRepository;

    @Autowired
    private TodoListRepository todoListRepository;

    @Autowired
    private HouseholdTodoListRepository householdTodoListRepository;

    public TodoTaskService() {

    }

    /**
     * gets all tasks, including the individual and households
     * @return the list of all tasks
     */
    public List<ITodoTask> getAllTasks() {

        List<ITodoTask> todoTasks = new ArrayList<>();
        todoTasks.addAll(this.todoTaskRepository.findAll());
        todoTasks.addAll(this.householdTodoTaskRepository.findAll());

        return todoTasks;
    }

    /**
     * gets all individual tasks in the database
     * @return the list of all individual tasks
     */
    public List<TodoTask> getAllIndividualTasks() {
        return this.todoTaskRepository.findAll();
    }

    /**
     * gets all household tasks in the database
     * @return the list of all household tasks
     */
    public List<HouseholdTodoTask> getAllHouseholdTasks() {
        return this.householdTodoTaskRepository.findAll();
    }

    /**
     * Finds an individual task by id
     * @param id the id to find by
     * @return the individual task
     */
    public TodoTask getIndividualTaskById(Long id) {
        return this.todoTaskRepository.findByTodoTaskId(id);
    }

    /**
     * Find a household task by id
     * @param id the id to find by
     * @return the household task
     */
    public HouseholdTodoTask getHouseholdTaskById(Long id) {
        return this.householdTodoTaskRepository.findByTodoTaskId(id);
    }

    /**
     * Adds a new individual task
     * @param todoTaskDTO the to do task dto
     * @return the new individual task
     */
    public TodoTask addIndividualTask(TodoTaskDTO todoTaskDTO) {
        TodoList todoList = this.todoListRepository.findByTodoListId(todoTaskDTO.getTodoListId());

        TodoTask todoTask = new TodoTask(todoTaskDTO, todoList);
        this.todoTaskRepository.save(todoTask);

        return todoTask;
    }

    /**
     * Updates a individual task
     * @param updateTaskDTO the update task dto
     * @return the updated task
     */
    public TodoTask updateIndividualTask(UpdateTaskDTO updateTaskDTO) {
        TodoTask todoTask = this.todoTaskRepository.findByTodoTaskId(updateTaskDTO.getTaskId());

        todoTask.setDescription(updateTaskDTO.getDescription());
        todoTask.setCompleted(updateTaskDTO.isCompleted());
        todoTask.setDueDate(updateTaskDTO.getDueDate());

        TodoTask updatedTask = this.todoTaskRepository.save(todoTask);

        return updatedTask;
    }

    /**
     * Updates a household task
     * @param updateTaskDTO the update task dto
     * @return the updated task
     */
    public HouseholdTodoTask updateHouseholdTask(UpdateTaskDTO updateTaskDTO) {
        HouseholdTodoTask householdTodoTask = this.householdTodoTaskRepository.findByTodoTaskId(updateTaskDTO.getTaskId());

        householdTodoTask.setDescription(updateTaskDTO.getDescription());
        householdTodoTask.setCompleted(updateTaskDTO.isCompleted());
        householdTodoTask.setDueDate(updateTaskDTO.getDueDate());

        HouseholdTodoTask updatedTask = this.householdTodoTaskRepository.save(householdTodoTask);

        return updatedTask;
    }

    /**
     * Adds a new household task to a list
     * @param todoTaskDTO the to do task dto
     * @return the new household task
     */
    public HouseholdTodoTask addHouseholdTask(TodoTaskDTO todoTaskDTO) {
        HouseholdTodoList householdTodoList = this.householdTodoListRepository.findByTodoListId(todoTaskDTO.getTodoListId());

        HouseholdTodoTask householdTodoTask = new HouseholdTodoTask(todoTaskDTO, householdTodoList);
        this.householdTodoTaskRepository.save(householdTodoTask);

        return householdTodoTask;
    }

    /**
     * Deletes an individual task
     * @param id the id of the task to delete
     * @return the deleted task
     */
    public TodoTask deleteIndividualTask(Long id) {
        TodoTask todoTask = this.todoTaskRepository.findByTodoTaskId(id);
        this.todoTaskRepository.delete(todoTask);

        return todoTask;
    }

    /**
     * Deletes a household task
     * @param id the id of the task to delete
     * @return the deleted task
     */
    public HouseholdTodoTask deleteHouseholdTask(Long id) {
        HouseholdTodoTask householdTodoTask = this.householdTodoTaskRepository.findByTodoTaskId(id);
        this.householdTodoTaskRepository.delete(householdTodoTask);

        return householdTodoTask;
    }
}
