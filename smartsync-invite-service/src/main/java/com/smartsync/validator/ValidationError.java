package com.smartsync.validator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jack Meyer
 *
 * The Validation Error
 */
public class ValidationError {

    /**
     * The list of errors
     */
    private List<String> errors = new ArrayList<>();

    /**
     * The error message
     */
    private final String errorMessage;


    public ValidationError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void addValidationError(String error) {
        errors.add(error);
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
