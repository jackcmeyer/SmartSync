package com.smartsync.validator;

import com.smartsync.dto.ServiceDTO;
import com.smartsync.dto.ServiceTypeDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Jack Meyer
 *
 * The Service Validator
 */
public class ServiceTypeValidator implements Validator {

    public boolean supports(Class clazz) {
        return ServiceTypeDTO.class.equals(clazz);
    }

    /**
     * Validates the input
     * @param object the user to validate
     * @param errors the errors
     */
    public void validate(Object object, Errors errors) {
//        ServiceTypeDTO serviceType = (ServiceTypeDTO) object;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required",
                "Service name must not be empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "field.required",
                "Service description must not be empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "isActive", "field.required",
                "Service isActive must not be empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "component", "field.required",
                "Service component must not be empty.");

    }


}
