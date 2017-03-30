package com.smartsync.error;

import com.smartsync.validator.ValidationError;

import java.util.List;

/**
 * @author Jack Meyer
 *
 * The Illegal Request Format Error Info Container
 */
public class IllegalRequestFormatErrorInfo {

    /**
     * The name of the error
     */
    private String name;

    /**
     * The error message
     */
    private String message;

    /**
     * The url at which the error ocurred
     */
    private String url;

    /**
     * The validation errors
     */
    private List<String> errors;

    public IllegalRequestFormatErrorInfo(String name, String messgae, String url, ValidationError errors) {
        this.name = name;
        this.message = messgae;
        this.url = url;
        this.errors = errors.getErrors();
    }

    public IllegalRequestFormatErrorInfo() {
        // default constructor
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
