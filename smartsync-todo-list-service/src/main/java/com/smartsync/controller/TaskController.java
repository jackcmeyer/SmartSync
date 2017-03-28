package com.smartsync.controller;

import com.smartsync.dto.TodoTaskDTO;
import com.smartsync.dto.UpdateTaskDTO;
import com.smartsync.model.HouseholdTodoTask;
import com.smartsync.model.TodoTask;
import com.smartsync.service.TodoTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

        return ResponseEntity.ok(householdTodoTask);
    }

    /**
     * Creates a new individual task
     * @param todoTaskDTO the to do task dto
     * @return the response entity with the new task
     */
    @RequestMapping(method = RequestMethod.POST, value = "/tasks/individual", produces = "application/json")
    public ResponseEntity addIndividualTask(@RequestBody TodoTaskDTO todoTaskDTO) {
        TodoTask todoTask = this.todoTaskService.addIndividualTask(todoTaskDTO);

        return ResponseEntity.ok(todoTask);
    }

    /**
     * Cretes a new household task
     * @param todoTaskDTO the household to do task dto
     * @return the response entity with the new task
     */
    @RequestMapping(method = RequestMethod.POST, value = "/tasks/household", produces = "application/json")
    public ResponseEntity addHouseholdTask(@RequestBody TodoTaskDTO todoTaskDTO) {
        HouseholdTodoTask householdTodoTask = this.todoTaskService.addHouseholdTask(todoTaskDTO);

        return ResponseEntity.ok(householdTodoTask);
    }

    /**
     * Uupdates an individual task
     * @param updateTaskDTO the update task dto
     * @return response entity with the updated task
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/tasks/individual", produces = "application/json")
    public ResponseEntity updateIndividualTask(@RequestBody UpdateTaskDTO updateTaskDTO) {

        System.out.println(updateTaskDTO);

        TodoTask todoTask = this.todoTaskService.updateIndividualTask(updateTaskDTO);

        return ResponseEntity.ok(todoTask);
    }

    /**
     * Updates a household task
     * @param updateTaskDTO the update task dto
     * @return response entity with the updated task
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/tasks/household", produces = "application/json")
    public ResponseEntity updateHouseholdTask(@RequestBody UpdateTaskDTO updateTaskDTO) {
        HouseholdTodoTask householdTodoTask = this.todoTaskService.updateHouseholdTask(updateTaskDTO);

        return ResponseEntity.ok(householdTodoTask);
    }

    /**
     * Deletes a task by id
     * @param id the id to delete by
     * @return response entity with the deleted task
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/tasks/individual/{id}", produces = "application/json")
    public ResponseEntity deleteIndividualTask(@PathVariable("id") Long id) {
        TodoTask todoTask = this.todoTaskService.deleteIndividualTask(id);

        return ResponseEntity.ok(todoTask);
    }

    /**
     * Deletes a household task by id
     * @param id the id to delete by
     * @return response entity with the deleted task
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/tasks/household/{id}", produces = "application/json")
    public ResponseEntity deleteHouseholdTask(@PathVariable("id") Long id) {
        HouseholdTodoTask householdTodoTask = this.todoTaskService.deleteHouseholdTask(id);

        return ResponseEntity.ok(householdTodoTask);
    }
}
