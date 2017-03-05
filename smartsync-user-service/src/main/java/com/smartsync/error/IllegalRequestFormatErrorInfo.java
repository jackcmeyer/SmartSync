package com.smartsync.error;

import com.smartsync.validator.ValidationError;
import org.springframework.validation.Errors;

import java.util.List;

/**
 * Created by jack on 3/5/17.
 */
public class IllegalRequestFormatErrorInfo {

    private String name;

    private String message;

    private String url;

    private ValidationError errors;

    public IllegalRequestFormatErrorInfo(String name, String messgae, String url, ValidationError errors) {
        this.name = name;
        this.message = messgae;
        this.url = url;
        this.errors = errors;
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

    public ValidationError getErrors() {
        return errors;
    }

    public void setErrors(ValidationError errors) {
        this.errors = errors;
    }
}
