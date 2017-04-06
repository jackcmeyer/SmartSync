package com.smartsync.validator;

import com.smartsync.dto.UpdateHouseholdDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by gregory on 4/1/17.
 */
public class UpdateHouseholdValidator implements Validator{

    public boolean supports(Class clazz) {
        return UpdateHouseholdDTO.class.equals(clazz);
    }

    /**
     * validates the data
     * @param object the household to validate
     * @param errors the errors
     */
    public void validate(Object object, Errors errors) {
        UpdateHouseholdDTO household = (UpdateHouseholdDTO) object;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "householdName", "field.required",
                "Household name must not be empty");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ownerId", "field.required",
                "Owner ID must not be empty.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstAddressLine", "filed.required",
                "First Address Line must not be empty.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "field.required",
                "City must not be empty.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state", "field.required",
                "State must not be empty.");

        /**
         * Doing specific validation, must not have any errors from above
         */
        if(!errors.hasErrors()) {
            if(Integer.toString(household.getZipCode()).length() != 5) {
                errors.rejectValue("zipCode", "field.length", "Zip Code must be 5 digits");
            }

            if(household.getState().length() != 2) {
                errors.rejectValue("state", "field.length",
                        "State Code must be 2 characters long. Did you send the full length name instead?");
            }
        }
    }
}

