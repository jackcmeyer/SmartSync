package com.smartsync.validator;

import com.smartsync.dto.UpdateServiceDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The update user validator
 */
public class UpdateServiceValidator implements Validator {
    public boolean supports(Class clazz) {
        return UpdateServiceDTO.class.equals(clazz);
    }

    /**
     * Validates the input
     * @param object the user to validate
     * @param errors the errors
     */
    public void validate(Object object, Errors errors) {

        UpdateServiceDTO user = (UpdateServiceDTO) object;

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
