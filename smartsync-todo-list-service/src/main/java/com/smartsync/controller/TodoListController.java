package com.smartsync.controller;

import com.smartsync.dto.HouseholdTodoListDTO;
import com.smartsync.dto.TodoListDTO;
import com.smartsync.model.HouseholdTodoList;
import com.smartsync.model.ITodoList;
import com.smartsync.model.TodoList;
import com.smartsync.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

        return ResponseEntity.ok(householdTodoList);
    }


    /**
     * Adds a new individual to do list
     * @return the response entity with the new to do list
     */
    @RequestMapping(method = RequestMethod.POST, value = "/individual", produces = "application/json")
    public ResponseEntity addIndividualTodoList(@RequestBody TodoListDTO todoListDTO) {

        System.out.println(todoListDTO);
        TodoList todoList = this.todoListService.addIndividualTodoList(todoListDTO);

        return ResponseEntity.ok(todoList);
    }

    /**
     * Adds a new household to do list
     * @param householdTodoListDTO the household to do list dto to add
     * @return response entity with the new household to do list
     */
    @RequestMapping(method = RequestMethod.POST, value = "/household", produces = "application/json")
    public ResponseEntity addHouseholdTodoList(@RequestBody HouseholdTodoListDTO householdTodoListDTO) {
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
        TodoList todoList = this.todoListService.deleteIndividualTodoListById(id);

        return ResponseEntity.ok(todoList);
    }

    /**
     * Gets the household to do list with the id
     * @param id the id to find by
     * @return the response entity with the household to do list
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/household/{id}", produces = "application/json")
    public ResponseEntity deleteHouseholdTodoListById(@PathVariable("id") Long id) {
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
        List<HouseholdTodoList> householdTodoLists = this.todoListService.getHouseholdTodoListsForHousehold(householdId);

        return ResponseEntity.ok(householdTodoLists);
    }

}
