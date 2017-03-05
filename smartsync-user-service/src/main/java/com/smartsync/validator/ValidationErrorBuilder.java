package com.smartsync.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

/**
 * @author Jack Meyer
 *
 * The Validation Error Builder
 */
public class ValidationErrorBuilder {

    /**
     * Takes in the list of errors from the Validator and turns them into readable strings for the response to the
     * front end
     *
     * @param errors the errors
     *
     * @return the Validation Error container
     */
    public static ValidationError fromBindErrors(Errors errors) {
        ValidationError error = new ValidationError("Illegal Request Format");

        for(ObjectError objectError : errors.getAllErrors()) {
            error.addValidationError(objectError.getDefaultMessage());
        }

        return error;

    }

}
