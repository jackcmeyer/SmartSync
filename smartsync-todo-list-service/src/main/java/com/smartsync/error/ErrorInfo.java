package com.smartsync.error;

import java.util.Date;

/**
 * @author Jack Meyer
 *
 * The basic Error Info Container
 */
public class ErrorInfo {

    /**
     * The name of the error
     */
    private String name;

    /**
     * The error message
     */
    private String message;

    /**
     * The url at which the error occurred
     */
    private String url;

    /**
     * The time at which the error occurred
     */
    private Date time;

    public ErrorInfo(String name, String message, String url) {
        this.name = name;
        this.message = message;
        this.url = url;
        this.time = new Date();
    }

    public ErrorInfo() {
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
}
