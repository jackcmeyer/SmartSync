package com.smartsync.controller;

import com.smartsync.dto.HouseholdTodoListDTO;
import com.smartsync.dto.TodoListDTO;
import com.smartsync.error.*;
import com.smartsync.model.HouseholdTodoList;
import com.smartsync.model.ITodoList;
import com.smartsync.model.TodoList;
import com.smartsync.service.TodoListService;
import com.smartsync.validator.HouseholdTodoListValidator;
import com.smartsync.validator.TodoListValidator;
import com.smartsync.validator.ValidationError;
import com.smartsync.validator.ValidationErrorBuilder;
import communication.HouseholdServiceCommunication;
import communication.UserServiceCommunication;
import model.HouseholdPOJO;
import model.UserPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The to do list controller
 */
@RestController
public class TodoListController {

    @Autowired
    private TodoListService todoListService;

    public TodoListController() {

    }

    /**
     * Get all to do lists, both household and individual
     * @return the response entity with the to do list
     */
    @RequestMapping(method = RequestMethod.GET, value = "/", produces = "application/json")
    public ResponseEntity getAllTodoLists() {
        List<ITodoList> todoLists = this.todoListService.getAll();

        return ResponseEntity.ok(todoLists);
    }

    /**
     * Gets all the individual to do lists
     * @return response entity with the individual to do lists
     */
    @RequestMapping(method = RequestMethod.GET, value = "/individual", produces = "application/json")
    public ResponseEntity getAllIndividualTodoLists() {
        List<TodoList> todoLists = this.todoListService.getAllIndividualTodoLists();

        return ResponseEntity.ok(todoLists);
    }

    /**
     * Gets all of the household to do lists
     * @return response entity with the household to do lists
     */
    @RequestMapping(method = RequestMethod.GET, value = "/household", produces = "application/json")
    public ResponseEntity getAllHouseholdTodoLists() {
        List<HouseholdTodoList> householdTodoLists = this.todoListService.getAllHouseholdTodoLists();

        return ResponseEntity.ok(householdTodoLists);
    }

    /**
     * Gets the individual to do list with the id
     * @param id the id to find by
     * @return the response entity with individual
     */
    @RequestMapping(method = RequestMethod.GET, value = "/individual/{id}", produces = "application/json")
    public ResponseEntity getIndividualTodoListById(@PathVariable("id") Long id) {
        TodoList todoList = this.todoListService.getIndividualTodoListById(id);

        // check if the to do list exists
        if(todoList == null) {
            String message = "Could not find to do list with id " + id;
            String url = "/todolist/individual/" + id;

            throw new TodoListNotFoundException(message, url);
        }

        return ResponseEntity.ok(todoList);
    }

    /**
     * Gets the household to do list with the id
     * @param id the id to find by
     * @return the response entity with the household to do list
     */
    @RequestMapping(method = RequestMethod.GET, value = "/household/{id}", produces = "application/json")
    public ResponseEntity getHouseholdTodoListById(@PathVariable("id") Long id) {
        HouseholdTodoList householdTodoList = this.todoListService.getHouseholdTodoListById(id);

        // check if the to do list exists
        if(householdTodoList == null) {
            String message = "Could not find to do list with id " + id;
            String url = "/todolist/household/" + id;

            throw new TodoListNotFoundException(message, url);
        }

        return ResponseEntity.ok(householdTodoList);
    }


    /**
     * Adds a new individual to do list
     * @return the response entity with the new to do list
     */
    @RequestMapping(method = RequestMethod.POST, value = "/individual", produces = "application/json")
    public ResponseEntity addIndividualTodoList(@RequestBody TodoListDTO todoListDTO, Errors errors) {

        // check for validation errors
        TodoListValidator todoListValidator = new TodoListValidator();
        todoListValidator.validate(todoListDTO, errors);
        if(errors.hasErrors()) {
            String message = "Could not add new to do list " + todoListDTO;
            String url = "/todolist/individual";

            ValidationError validationError = ValidationErrorBuilder.fromBindErrors(errors);

            throw new IllegalRequestFormatException(message, url, validationError);
        }

        // check if the user for the to do list exists
        UserServiceCommunication userServiceCommunication = new UserServiceCommunication();
        UserPOJO userPOJO = userServiceCommunication.getUser(todoListDTO.getUserId());
        if(userPOJO == null) {
            String message = "Could not find user with id " + todoListDTO.getUserId();
            String url = "todolist/individual";

            throw new UserNotFoundException(message, url);
        }

        TodoList todoList = this.todoListService.addIndividualTodoList(todoListDTO);
        return ResponseEntity.ok(todoList);
    }

    /**
     * Adds a new household to do list
     * @param householdTodoListDTO the household to do list dto to add
     * @return response entity with the new household to do list
     */
    @RequestMapping(method = RequestMethod.POST, value = "/household", produces = "application/json")
    public ResponseEntity addHouseholdTodoList(@RequestBody HouseholdTodoListDTO householdTodoListDTO, Errors errors) {

        // check for validation errors
        HouseholdTodoListValidator householdTodoListValidator = new HouseholdTodoListValidator();
        householdTodoListValidator.validate(householdTodoListDTO, errors);
        if(errors.hasErrors()) {
            String message = "Could not add new to do list " + householdTodoListDTO.getHouseholdId();
            String url = "/todolist/household";

            ValidationError validationError = ValidationErrorBuilder.fromBindErrors(errors);
            throw new IllegalRequestFormatException(message, url, validationError);
        }

        // check if the household exists
        HouseholdServiceCommunication householdServiceCommunication = new HouseholdServiceCommunication();
        HouseholdPOJO householdPOJO =
                householdServiceCommunication.getHouseholdByHouseholdId(householdTodoListDTO.getHouseholdId());
        if(householdPOJO == null) {
            String message = "Could not find household with id " + householdTodoListDTO.getHouseholdId();
            String url = "/todolist/household";

            throw new HouseholdNotFoundException(message, url);
        }


        HouseholdTodoList householdTodoList = this.todoListService.addHouseholdTodoList(householdTodoListDTO);
        return ResponseEntity.ok(householdTodoList);
    }

    /**
     * Deletes the individual to do list with the id
     * @param id the id to find by
     * @return the response entity with individual
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/individual/{id}", produces = "application/json")
    public ResponseEntity deleteIndividualTodoListById(@PathVariable("id") Long id) {

        // check for if the to do list exists
        TodoList todoList = this.todoListService.getIndividualTodoListById(id);
        if(todoList == null) {
            String message = "Could not delete to do list with id " + id + " because it does not exist";
            String url = "/todolist/individual/" + id;

            throw new TodoListNotFoundException(message, url);
        }


        TodoList deletedTodoList = this.todoListService.deleteIndividualTodoListById(id);
        return ResponseEntity.ok(deletedTodoList);
    }

    /**
     * Gets the household to do list with the id
     * @param id the id to find by
     * @return the response entity with the household to do list
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/household/{id}", produces = "application/json")
    public ResponseEntity deleteHouseholdTodoListById(@PathVariable("id") Long id) {

        // check for if the to do list exists
        HouseholdTodoList todoList = this.todoListService.getHouseholdTodoListById(id);
        if(todoList == null) {
            String message = "Could not delete to do list with id " + id + " because it does not exist";
            String url = "/todolist/household/" + id;

            throw new TodoListNotFoundException(message, url);
        }


        HouseholdTodoList householdTodoList = this.todoListService.deleteHouseholdTodoListById(id);
        return ResponseEntity.ok(householdTodoList);
    }

    /**
     * Gets all of the to do lists for a user, including individual and household
     * @param userId the user id to find by
     * @return the response entity with the to do lists
     */
    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}", produces = "application/json")
    public ResponseEntity getAllTodoListsForUser(@PathVariable("userId") Long userId) {

        // check if the user for the to do list exists
        UserServiceCommunication userServiceCommunication = new UserServiceCommunication();
        UserPOJO userPOJO = userServiceCommunication.getUser(userId);
        if(userPOJO == null) {
            String message = "Could not find user with id " + userId;
            String url = "todolist/individual";

            throw new UserNotFoundException(message, url);
        }


        List<ITodoList> todoLists = this.todoListService.getTodoListsForUser(userId);
        return ResponseEntity.ok(todoLists);
    }

    /**
     * Gets all individual to do lists for a user
     * @param userId the uesr id to find by
     * @return the response entity with the to do lists
     */
    @RequestMapping(method = RequestMethod.GET, value = "/individual/users/{userId}", produces = "application/json")
    public ResponseEntity getAllIndividualTodoListsForUser(@PathVariable("userId") Long userId) {

        // check if the user for the to do list exists
        UserServiceCommunication userServiceCommunication = new UserServiceCommunication();
        UserPOJO userPOJO = userServiceCommunication.getUser(userId);
        if(userPOJO == null) {
            String message = "Could not find user with id " + userId;
            String url = "todolist/individual";

            throw new UserNotFoundException(message, url);
        }


        List<TodoList> todoLists = this.todoListService.getIndividualTodoListsForUser(userId);
        return ResponseEntity.ok(todoLists);
    }

    /**
     * Gets all of the to do lists for a household
     * @param householdId the household to find by
     * @return the response entity with the household
     */
    @RequestMapping(method = RequestMethod.GET, value = "/household/{householdId}", produces = "application/json")
    public ResponseEntity getAllHouseholdTodoListsForHousehold(@PathVariable("householdId") Long householdId) {

        // check if the household exists
        HouseholdServiceCommunication householdServiceCommunication = new HouseholdServiceCommunication();
        HouseholdPOJO householdPOJO =
                householdServiceCommunication.getHouseholdByHouseholdId(householdId);
        if(householdPOJO == null) {
            String message = "Could not find household with id " + householdId;
            String url = "/todolist/household";

            throw new HouseholdNotFoundException(message, url);
        }

        List<HouseholdTodoList> householdTodoLists = this.todoListService.getHouseholdTodoListsForHousehold(householdId);
        return ResponseEntity.ok(householdTodoLists);
    }

    /**
     * Handles the user not found exception
     * @param e the user not found exception
     * @return the response entity with the error
     */
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity handleUserNotFoundException(UserNotFoundException e) {
        ErrorInfo error = new ErrorInfo("User Not Found", e.getMessage(), e.getUrl());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    /**
     * Handles the household not found exception
     * @param e the household not found exception
     * @return the response entity with the error
     */
    @ExceptionHandler(value = HouseholdNotFoundException.class)
    public ResponseEntity handleHouseholdNotFoundException(HouseholdNotFoundException e) {
        ErrorInfo error = new ErrorInfo("Household Not Found", e.getMessage(), e.getUrl());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
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
