package com.smartsync.error;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com))
 *
 * The Error Information Container
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
     * The url at which the error occured
     */
    private String url;

    public ErrorInfo(String name, String message, String url) {
        this.name = name;
        this.message = message;
        this.url = url;
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
