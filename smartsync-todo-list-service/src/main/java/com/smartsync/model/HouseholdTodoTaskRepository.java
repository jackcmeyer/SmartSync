package com.smartsync.model;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The household to do task repository
 */
public interface HouseholdTodoTaskRepository extends JpaRepository<HouseholdTodoTask, Long> {

    /**
     * Finds a to do task by id
     * @param todoTaskId the to do task id to find by
     * @return the to do task
     */
    HouseholdTodoTask findByTodoTaskId(Long todoTaskId);

}
