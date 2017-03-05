package com.smartsync.error;

/**
 * @author Jack Meyer
 *
 * The Duplicate User Exception
 */
public class DuplicateUserException extends RuntimeException{

    /**
     * The error message
     */
    private String message;

    /**
     * The url which the error occurred at
     */
    private String url;

    public DuplicateUserException(String message, String url) {
        this.message = message;
        this.url = url;
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
}
