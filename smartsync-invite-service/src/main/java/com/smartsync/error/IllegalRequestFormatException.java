package com.smartsync.error;

import com.smartsync.validator.ValidationError;

/**
 * @author Jack Meyer
 *
 * The Illegal Request Format Exception
 */
public class IllegalRequestFormatException extends RuntimeException {

    /**
     * The exception message
     */
    private String message;

    /**
     * The url which the exception occured at
     */
    private String url;

    /**
     * The list of errors
     */
    private ValidationError errors;

    public IllegalRequestFormatException(String message, String url, ValidationError errors) {
        this.message = message;
        this.url = url;
        this.errors = errors;
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

    public ValidationError getErrors() {
        return errors;
    }

    public void setErrors(ValidationError errors) {
        this.errors = errors;
    }
}
