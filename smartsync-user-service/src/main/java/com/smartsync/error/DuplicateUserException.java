package com.smartsync.error;

/**
 * Created by jack on 3/5/17.
 */
public class DuplicateUserException extends RuntimeException{

    private String message;

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
