package com.smartsync.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

/**
 * Created by jack on 3/5/17.
 */
public class ValidationErrorBuilder {

    public static ValidationError fromBindErrors(Errors errors) {
        ValidationError error = new ValidationError("Illegal Request Format");

        for(ObjectError objectError : errors.getAllErrors()) {
            error.addValidationError(objectError.getDefaultMessage());
        }

        return error;

    }

}
