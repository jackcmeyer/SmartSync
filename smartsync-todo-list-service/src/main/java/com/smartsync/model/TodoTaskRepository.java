package com.smartsync.model;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The to do task repository
 */
public interface TodoTaskRepository extends JpaRepository<TodoTask, Long> {

    /**
     * Finds a to do task by id
     * @param todoTaskId the to do task id to find by
     * @return the to do task
     */
    TodoTask findByTodoTaskId(Long todoTaskId);
}
