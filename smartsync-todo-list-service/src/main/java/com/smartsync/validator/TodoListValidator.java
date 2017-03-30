package com.smartsync.validator;

import com.smartsync.dto.TodoListDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The To Do List Validator
 */
public class TodoListValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return TodoListDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        TodoListDTO todoListDTO = (TodoListDTO) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userId", "field.required",
                "User Id must not be empty.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required",
                "Name must not be empty.");
    }
}
