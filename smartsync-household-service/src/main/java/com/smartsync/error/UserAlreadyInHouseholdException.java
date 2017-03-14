package com.smartsync.error;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The user already in household exception
 */
public class UserAlreadyInHouseholdException extends RuntimeException {

    /**
     * The url at which the error occurred
     */
    private String url;

    /**
     * The error message
     */
    private String message;

    public UserAlreadyInHouseholdException(String message, String url) {
        this.url = url;
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

