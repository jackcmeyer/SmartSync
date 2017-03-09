package com.smartsync.error;

/**
 * @author Jack Meyer
 *
 * The Household Not Found Exception. Thrown when there was not a household found.
 */
public class HouseholdNotFoundException extends RuntimeException {

    /**
     * The url at which the error occurred
     */
    private String url;

    /**
     * The error message
     */
    private String message;

    public HouseholdNotFoundException(String message, String url) {
        this.url = url;
        this.message = message;
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
