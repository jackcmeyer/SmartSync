package com.smartsync.error;

/**
 * Created by trev on 4/2/17.
 */
public class ClientNotAuthorizedException extends RuntimeException {
    private String url;

    private String message;

    public ClientNotAuthorizedException(String message, String url) {
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
