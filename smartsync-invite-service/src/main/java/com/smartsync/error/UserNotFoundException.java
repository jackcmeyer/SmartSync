package com.smartsync.error;


/**
 * @author Jack Meyer
 *
 * User Not Found Exception which is thrown when the user that is tyring to be accessed is not found in the database.
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * The url which the error occured
     */
    private String url;

    /**
     * The error message
     */
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
