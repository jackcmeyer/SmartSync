package com.smartsync.service;

import com.smartsync.dto.HouseholdTodoListDTO;
import com.smartsync.dto.TodoListDTO;
import com.smartsync.model.*;
import communication.HouseholdServiceCommunication;
import model.HouseholdPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The to do list service
 */
@Component
public class TodoListService {

    @Autowired
    private TodoListRepository todoListRepository;

    @Autowired
    private HouseholdTodoListRepository householdTodoListRepository;

    public TodoListService() {

    }

    /**
     * Gets all to do list
     * @return
     */
    public List<ITodoList> getAll() {

        List<ITodoList> todoLists = new ArrayList<>();
        todoLists.addAll(this.todoListRepository.findAll());
        todoLists.addAll(this.householdTodoListRepository.findAll());

        return todoLists;
    }

    /**
     * Gets all to do lists
     * @return
     */
    public List<TodoList> getAllIndividualTodoLists() {
        return this.todoListRepository.findAll();
    }

    /**
     * Get all household to do lists
     */
    public List<HouseholdTodoList> getAllHouseholdTodoLists() {
        return this.householdTodoListRepository.findAll();
    }

    /**
     * Gets the individual to do list by id
     * @param id the id to find by
     * @return the to do list
     */
    public TodoList getIndividualTodoListById(Long id) {
        return this.todoListRepository.findByTodoListId(id);
    }

    /**
     * Gets the household to do list by id
     * @param id the id to find by
     * @return the household to do list
     */
    public HouseholdTodoList getHouseholdTodoListById(Long id) {
        return this.householdTodoListRepository.findByTodoListId(id);
    }

    /**
     * Adds a new individual to do list
     * @param todoListDTO the to do list dto to add
     * @return the new to do list
     */
    public TodoList addIndividualTodoList(TodoListDTO todoListDTO) {

        TodoList todoList = new TodoList(todoListDTO);
        this.todoListRepository.save(todoList);

        return todoList;
    }

    /**
     * Adds a new household to do list
     * @param householdTodoListDTO the hosuehold to do list dto to add
     * @return the new household to do list
     */
    public HouseholdTodoList addHouseholdTodoList(HouseholdTodoListDTO householdTodoListDTO) {
        HouseholdTodoList householdTodoList = new HouseholdTodoList(householdTodoListDTO);
        this.householdTodoListRepository.save(householdTodoList);

        return householdTodoList;
    }

    /**
     * Deletes a individual to do list by id
     * @param id the id to delete by
     * @return the deleted to do list
     */
    public TodoList deleteIndividualTodoListById(Long id) {
        TodoList todoList = this.todoListRepository.findByTodoListId(id);
        this.todoListRepository.delete(todoList);

        return todoList;
    }

    /**
     * Deletes a hosuehold to do list by id
     * @param id the id to delete by
     * @return the deleted to do list
     */
    public HouseholdTodoList deleteHouseholdTodoListById(Long id) {
        HouseholdTodoList householdTodoList = this.householdTodoListRepository.findByTodoListId(id);
        this.householdTodoListRepository.delete(householdTodoList);

        return householdTodoList;
    }

    /**
     * Gets all to do lists for a user, includes all individual and all household lists which the user belongs to
     * @param userId the user id to find by
     * @return
     */
    public List<ITodoList> getTodoListsForUser(Long userId) {

        HouseholdServiceCommunication householdServiceCommunication = new HouseholdServiceCommunication();
        HouseholdPOJO householdPOJO =  householdServiceCommunication.getHouseholdForUserId(userId);

        List<ITodoList> todoLists = new ArrayList<>();
        todoLists.addAll(this.todoListRepository.findByUserId(userId));
        todoLists.addAll(this.householdTodoListRepository.findByHouseholdId(householdPOJO.getHouseholdId()));


        return todoLists;
    }

    /**
     * Gets individual to do lists for user
     * @param userId the user id to find by
     * @return the list of individual to do lists for a user
     */
    public List<TodoList> getIndividualTodoListsForUser(Long userId) {
        return this.todoListRepository.findByUserId(userId);
    }

    /**
     * Gets household to do lists for household
     * @param householdId the household id to find by
     * @return the list of household to do lists
     */
    public List<HouseholdTodoList> getHouseholdTodoListsForHousehold(Long householdId) {
        return this.householdTodoListRepository.findByHouseholdId(householdId);
    }



}
