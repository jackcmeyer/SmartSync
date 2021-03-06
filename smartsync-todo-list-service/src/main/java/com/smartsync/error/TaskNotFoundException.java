package com.smartsync.error;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The task not found exception
 */
public class TaskNotFoundException extends RuntimeException {

    /**
     * The url which the error occured
     */
    private String url;

    /**
     * The error message
     */
    private String message;

    public TaskNotFoundException(String message, String url) {
        this.message = message;
        this.url = url;
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
