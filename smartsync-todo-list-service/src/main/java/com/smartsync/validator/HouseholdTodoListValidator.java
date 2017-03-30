package com.smartsync.validator;

import com.smartsync.dto.HouseholdTodoListDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The Household To Do List Validator
 */
public class HouseholdTodoListValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return HouseholdTodoListDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        HouseholdTodoListDTO householdTodoListDTO = (HouseholdTodoListDTO) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "householdId", "field.required",
                "Household Id must not be empty.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required",
                "Name must not be empty.");
    }
}
