package com.smartsync.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The to do list repository
 */
public interface TodoListRepository extends JpaRepository<TodoList, Long> {

    /**
     * Finds the to do by id
     * @param todoListId the id to find by
     * @return the to do list with that id
     */
    TodoList findByTodoListId(Long todoListId);

    /**
     * Finds the list of to do lists for a user
     * @param userId the user to find by
     * @return the list of to do lists for a user
     */
    List<TodoList> findByUserId(Long userId);

}
