package com.smartsync.validator;

import com.smartsync.dto.UpdateTaskDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The To Do List Validator
 */
public class UpdateTaskValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return UpdateTaskDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        UpdateTaskDTO updateTaskDTO = (UpdateTaskDTO) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "taskId", "field.required",
                "User Id must not be empty.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "field.required", "" +
                "Description must not be empty.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dueDate", "field.required",
                "Due Date must not be empty");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "isCompleted", "field.required",
                "Is Completed must not be empty");
    }
}
