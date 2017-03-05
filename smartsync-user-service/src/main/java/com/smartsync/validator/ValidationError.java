package com.smartsync.validator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jack on 3/5/17.
 */
public class ValidationError {

    private List<String> errors = new ArrayList<>();

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
