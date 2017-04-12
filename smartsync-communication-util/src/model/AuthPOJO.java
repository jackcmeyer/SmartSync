package model;

/**
 * Created by trev on 4/1/17.
 */
public class AuthPOJO {


    private String googleId;

    private String sessionId;

    private int role;

    public AuthPOJO(String googleId, String sessionId, int role) {
        this.googleId = googleId;
        this.sessionId = sessionId;
        this.role = role;

    }

    public String getGoogleId() {
        return googleId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public int getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "AuthPOJO{" +
                "\n\tgoogleId=" + googleId + "\"" +
                ",\n\tsessionId=" + sessionId +
                ",\n\tisAdmin=" + role +
                "\n}";
    }


}
