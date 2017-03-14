package com.smartsync.validator;

import com.smartsync.dto.AddUserToHouseholdDTO;
import com.smartsync.dto.HouseholdDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * Validates the add user process
 */
public class AddUserValidator implements Validator {


    public boolean supports(Class clazz) {
        return HouseholdDTO.class.equals(clazz);
    }

    /**
     * validates the data
     * @param object the household to validate
     * @param errors the errors
     */
    public void validate(Object object, Errors errors) {
        AddUserToHouseholdDTO addUserToHouseholdDTO = (AddUserToHouseholdDTO) object;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userId", "field.required",
                "User ID must not be empty.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "householdId", "field.required",
                "Household ID must not be empty.");

    }
}
