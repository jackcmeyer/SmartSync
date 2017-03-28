package com.smartsync.service;

import com.smartsync.model.HouseholdTodoTaskRepository;
import com.smartsync.model.TodoTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public TodoTaskService() {

    }


}
