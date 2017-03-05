package com.smartsync.error;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Jack Meyer
 *
 * User Not Found Exception which is thrown when the user that is tyring to be accessed is not found in the database.
 */
public class UserNotFoundException extends RuntimeException {

    private String url;

    private String message;

    public UserNotFoundException(String message, String url) {
        this.message = message;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
