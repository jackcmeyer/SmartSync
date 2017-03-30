package com.smartsync.validator;

import com.smartsync.dto.TodoTaskDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The To Do List Validator
 */
public class TodoTaskValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return TodoTaskDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        TodoTaskDTO todoTaskDTO = (TodoTaskDTO) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "todoListId", "field.required",
                "To Do List Id must not be empty.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "field.required",
                "Description must not be empty.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dueDate", "field.required",
                "Due Date must not be empty.");
    }
}
