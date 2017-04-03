package com.smartsync.validator;

import com.smartsync.dto.HouseholdDTO;
import com.smartsync.dto.ServiceAndHouseholdDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * Validates the add user process
 */
public class ServiceAndHouseholdValidator implements Validator {


    public boolean supports(Class clazz) {
        return HouseholdDTO.class.equals(clazz);
    }

    /**
     * validates the data
     * @param object the household to validate
     * @param errors the errors
     */
    public void validate(Object object, Errors errors) {
        ServiceAndHouseholdDTO serviceAndHouseholdDTO = (ServiceAndHouseholdDTO) object;

        if(serviceAndHouseholdDTO.getServiceId() == null) {
            errors.rejectValue("serviceId", "field.required", "Service ID must not be empty.");
        }

        if(serviceAndHouseholdDTO.getHouseholdId() == null) {
            errors.rejectValue("householdId", "field.required", "Household ID must not be empty.");
        }


//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userId", "field.required",
//                "User ID must not be empty.");
//
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "householdId", "field.required",
//                "Household ID must not be empty.");

    }
}
