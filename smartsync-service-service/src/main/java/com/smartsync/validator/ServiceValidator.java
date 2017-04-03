package com.smartsync.validator;

import com.smartsync.dto.ServiceDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Jack Meyer
 *
 * The Service Validator
 */
public class ServiceValidator implements Validator {

    public boolean supports(Class clazz) {
        return ServiceDTO.class.equals(clazz);
    }

    /**
     * Validates the input
     * @param object the user to validate
     * @param errors the errors
     */
    public void validate(Object object, Errors errors) {

        ServiceDTO user = (ServiceDTO) object;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required",
                "Service name must not be empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "field.required",
                "Service description must not be empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "isActive", "field.required",
                "Service isActive must not be empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "wide", "field.required",
                "Service wide must not be empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tall", "field.required",
                "Service tall must not be empty.");


    }


}
