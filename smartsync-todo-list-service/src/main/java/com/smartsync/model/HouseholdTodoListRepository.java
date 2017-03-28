package com.smartsync.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The household to do list repository
 */
public interface HouseholdTodoListRepository extends JpaRepository<HouseholdTodoList, Long> {

    /**
     * Finds the to do by id
     * @param todoListId the id to find by
     * @return the to do list with that id
     */
    HouseholdTodoList findByTodoListId(Long todoListId);

    /**
     * Finds the list of to do lists for a user
     * @param householdId the user to find by
     * @return the list of to do lists for a user
     */
    List<HouseholdTodoList> findByHouseholdId(Long householdId);

}
