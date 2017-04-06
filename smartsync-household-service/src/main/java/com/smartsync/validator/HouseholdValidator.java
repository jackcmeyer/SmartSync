package com.smartsync.validator;

import com.smartsync.dto.HouseholdDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The household validator class. Validates the data sent into the api and adds any errors to the errors object
 */
public class HouseholdValidator implements Validator {


    public boolean supports(Class clazz) {
        return HouseholdDTO.class.equals(clazz);
    }

    /**
     * validates the data
     * @param object the household to validate
     * @param errors the errors
     */
    public void validate(Object object, Errors errors) {
        HouseholdDTO household = (HouseholdDTO) object;

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
