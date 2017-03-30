package com.smartsync.controller;

import com.smartsync.dto.TodoTaskDTO;
import com.smartsync.dto.UpdateTaskDTO;
import com.smartsync.error.*;
import com.smartsync.model.HouseholdTodoList;
import com.smartsync.model.HouseholdTodoTask;
import com.smartsync.model.TodoList;
import com.smartsync.model.TodoTask;
import com.smartsync.service.TodoListService;
import com.smartsync.service.TodoTaskService;
import com.smartsync.validator.TodoTaskValidator;
import com.smartsync.validator.UpdateTaskValidator;
import com.smartsync.validator.ValidationError;
import com.smartsync.validator.ValidationErrorBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The task controller
 */
@RestController
public class TaskController {

    @Autowired
    private TodoTaskService todoTaskService;

    @Autowired
    private TodoListService todoListService;

    public TaskController() {

    }

    /**
     * Gets all tasks, household and individual
     * @return response entity with the list of tasks
     */
    @RequestMapping(method = RequestMethod.GET, value = "/tasks", produces = "application/json")
    public ResponseEntity getAllTasks() {

        return ResponseEntity.ok(this.todoTaskService.getAllTasks());
    }

    /**
     * Gets all individual tasks
     * @return response entity with the list of tasks
     */
    @RequestMapping(method = RequestMethod.GET, value = "/tasks/individual", produces = "application/json")
    public ResponseEntity getAllIndividualTasks() {

        return ResponseEntity.ok(this.todoTaskService.getAllIndividualTasks());
    }

    /**
     * Gets all household tasks
     * @return respones entity with the list of tasks
     */
    @RequestMapping(method = RequestMethod.GET, value = "/tasks/household", produces = "application/json")
    public ResponseEntity getAllHouseholdTasks() {

        return ResponseEntity.ok(this.todoTaskService.getAllHouseholdTasks());
    }

    /**
     * Gets individual task by id
     * @param id the id to find by
     * @return response entity with the task
     */
    @RequestMapping(method = RequestMethod.GET, value = "/tasks/individual/{id}", produces = "application/json")
    public ResponseEntity getIndividualTaskById(@PathVariable("id") Long id) {

        TodoTask todoTask = this.todoTaskService.getIndividualTaskById(id);

        // check if the to do list was found
        if(todoTask == null) {
            String message = "Task with id " + id + " could not be found.";
            String url = "todolist/tasks/individual/" + id;

            throw new TaskNotFoundException(message, url);
        }

        return ResponseEntity.ok(todoTask);
    }

    /**
     * Gets household task by id
     * @param id the id to find by
     * @return response entity the household task
     */
    @RequestMapping(method = RequestMethod.GET, value = "/tasks/household/{id}", produces = "application/json")
    public ResponseEntity getHouseholdTaskById(@PathVariable("id") Long id) {

        HouseholdTodoTask householdTodoTask = this.todoTaskService.getHouseholdTaskById(id);

        // check if the to do list was found
        if(householdTodoTask == null) {
            String message = "Task with id " + id + " could not be found.";
            String url = "todolist/tasks/household/" + id;

            throw new TaskNotFoundException(message, url);
        }

        return ResponseEntity.ok(householdTodoTask);
    }

    /**
     * Creates a new individual task
     * @param todoTaskDTO the to do task dto
     * @return the response entity with the new task
     */
    @RequestMapping(method = RequestMethod.POST, value = "/tasks/individual", produces = "application/json")
    public ResponseEntity addIndividualTask(@RequestBody TodoTaskDTO todoTaskDTO, Errors errors) {

        TodoTaskValidator todoTaskValidator = new TodoTaskValidator();
        todoTaskValidator.validate(todoTaskDTO, errors);

        // check if there are validation errors
        if(errors.hasErrors()) {
            String message = "Could not add new task.";
            String url = "/todolist/tasks/individual";

            ValidationError validationError = ValidationErrorBuilder.fromBindErrors(errors);

            throw new IllegalRequestFormatException(message, url, validationError);
        }

        // check if the to do list exists
        TodoList todoList = this.todoListService.getIndividualTodoListById(todoTaskDTO.getTodoListId());
        if(todoList == null) {

            String message = "Could not find to do list with id " + todoList.getTodoListId();
            String url = "/todolist/tasks/individual";

            throw new TodoListNotFoundException(message, url);
        }

        TodoTask todoTask = this.todoTaskService.addIndividualTask(todoTaskDTO);
        return ResponseEntity.ok(todoTask);
    }

    /**
     * Cretes a new household task
     * @param todoTaskDTO the household to do task dto
     * @return the response entity with the new task
     */
    @RequestMapping(method = RequestMethod.POST, value = "/tasks/household", produces = "application/json")
    public ResponseEntity addHouseholdTask(@RequestBody TodoTaskDTO todoTaskDTO, Errors errors) {

        TodoTaskValidator todoTaskValidator = new TodoTaskValidator();
        todoTaskValidator.validate(todoTaskDTO, errors);

        // check if there are validation errors
        if(errors.hasErrors()) {
            String message = "Could not add new task.";
            String url = "/todolist/tasks/household";

            ValidationError validationError = ValidationErrorBuilder.fromBindErrors(errors);

            throw new IllegalRequestFormatException(message, url, validationError);
        }

        // check if the to do list exists
        HouseholdTodoList householdTodoList = this.todoListService.getHouseholdTodoListById(todoTaskDTO.getTodoListId());
        if(householdTodoList == null) {
            String message = "Could not find to do list with id " + householdTodoList.getTodoListId();
            String url = "/todolist/tasks/household";

            throw new TodoListNotFoundException(message, url);
        }

        HouseholdTodoTask householdTodoTask = this.todoTaskService.addHouseholdTask(todoTaskDTO);
        return ResponseEntity.ok(householdTodoTask);
    }

    /**
     * Uupdates an individual task
     * @param updateTaskDTO the update task dto
     * @return response entity with the updated task
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/tasks/individual", produces = "application/json")
    public ResponseEntity updateIndividualTask(@RequestBody UpdateTaskDTO updateTaskDTO, Errors errors) {

        UpdateTaskValidator updateTaskValidator = new UpdateTaskValidator();
        updateTaskValidator.validate(updateTaskDTO, errors);

        // check if there are validation errors
        if(errors.hasErrors()) {
            String message = "Could not update task with id " + updateTaskDTO.getTaskId();
            String url = "/todolist/tasks/individual";

            ValidationError validationError = ValidationErrorBuilder.fromBindErrors(errors);

            throw new IllegalRequestFormatException(message, url, validationError);
        }

        // check if the task to update exists
        TodoTask todoTask = this.todoTaskService.getIndividualTaskById(updateTaskDTO.getTaskId());
        if(todoTask == null) {
            String message = "Could not find task with id " + updateTaskDTO.getTaskId();
            String url = "/todolist/tasks/individual";

            throw new TaskNotFoundException(message, url);
        }

        TodoTask updatedTodoTask = this.todoTaskService.updateIndividualTask(updateTaskDTO);
        return ResponseEntity.ok(updatedTodoTask);
    }

    /**
     * Updates a household task
     * @param updateTaskDTO the update task dto
     * @return response entity with the updated task
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/tasks/household", produces = "application/json")
    public ResponseEntity updateHouseholdTask(@RequestBody UpdateTaskDTO updateTaskDTO, Errors errors) {

        UpdateTaskValidator updateTaskValidator = new UpdateTaskValidator();
        updateTaskValidator.validate(updateTaskDTO, errors);

        // check if there are validation errors
        if(errors.hasErrors()) {
            String message = "Could not update task with id " + updateTaskDTO.getTaskId();
            String url = "/todolist/tasks/individual";

            ValidationError validationError = ValidationErrorBuilder.fromBindErrors(errors);

            throw new IllegalRequestFormatException(message, url, validationError);
        }

        // check if the task to update exists
        TodoTask todoTask = this.todoTaskService.getIndividualTaskById(updateTaskDTO.getTaskId());
        if(todoTask == null) {
            String message = "Could not find task with id " + updateTaskDTO.getTaskId();
            String url = "/todolist/tasks/household";

            throw new TaskNotFoundException(message, url);
        }

        HouseholdTodoTask updatedHouseholdTask = this.todoTaskService.updateHouseholdTask(updateTaskDTO);
        return ResponseEntity.ok(updatedHouseholdTask);
    }

    /**
     * Deletes a task by id
     * @param id the id to delete by
     * @return response entity with the deleted task
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/tasks/individual/{id}", produces = "application/json")
    public ResponseEntity deleteIndividualTask(@PathVariable("id") Long id) {

        // check if the to do list was found
        TodoTask todoTask = this.todoTaskService.getIndividualTaskById(id);
        if(todoTask == null) {
            String message = "Task with id " + id + " could not be deleted.";
            String url = "todolist/tasks/individual/" + id;

            throw new TaskNotFoundException(message, url);
        }

        TodoTask deletedTask = this.todoTaskService.deleteIndividualTask(id);
        return ResponseEntity.ok(deletedTask);
    }

    /**
     * Deletes a household task by id
     * @param id the id to delete by
     * @return response entity with the deleted task
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/tasks/household/{id}", produces = "application/json")
    public ResponseEntity deleteHouseholdTask(@PathVariable("id") Long id) {

        // check if the task was found
        HouseholdTodoTask householdTodoTask = this.todoTaskService.getHouseholdTaskById(id);
        if(householdTodoTask == null) {
            String message = "Task with id " + id + " could not be deleted.";
            String url = "todolist/tasks/individual/" + id;

            throw new TaskNotFoundException(message, url);
        }

        HouseholdTodoTask deleteHouseholdTask = this.todoTaskService.deleteHouseholdTask(id);
        return ResponseEntity.ok(deleteHouseholdTask);
    }

    /**
     * Handles the to do list not found exception
     * @param e the to do list not found exception
     * @return the response entity with the error
     */
    @ExceptionHandler(value = TodoListNotFoundException.class)
    public ResponseEntity handleTodoListNotFoundException(TodoListNotFoundException e) {

        ErrorInfo error = new ErrorInfo("Todo List Not Found", e.getMessage(), e.getUrl());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }


    /**
     * Handles the task not found exception
     * @param e the task not found exception
     * @return the response entity with the error
     */
    @ExceptionHandler(value = TaskNotFoundException.class)
    public ResponseEntity handleTaskNotFoundException(TaskNotFoundException e) {

        ErrorInfo error = new ErrorInfo("Task Not Found", e.getMessage(), e.getUrl());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    /**
     * Handles when there is an illegal request format exception. This includes missing parameters, improper input,
     * and other bad requests.
     * @param e the illegal request format exception
     * @return the response entity with the error
     */
    @ExceptionHandler(value = IllegalRequestFormatException.class)
    public ResponseEntity handleIllegalRequestFormatException(IllegalRequestFormatException e) {

        IllegalRequestFormatErrorInfo error = new IllegalRequestFormatErrorInfo("Illegal Request Format",
                e.getMessage(), e.getUrl(), e.getErrors());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
