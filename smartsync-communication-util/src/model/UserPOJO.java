package model;

/**
 * @author Jack Meyer
 *
 * The user data transfer object
 */
public class UserPOJO {

    /**
     * The user id
     */
    private Long userId;

    /**
     * The user's google id
     */
    private String googleId;

    /**
     * The user's full name
     */
    private String fullName;

    /**
     * The user's display name, similar to a username
     */
    private String displayName;

    /**
     * The user's given name
     */
    private String givenName;

    /**
     * The user's family name
     */
    private String familyName;

    /**
     * The user's image url. This is a link to their google profile picture
     */
    private String imageURL;

    /**
     * The user's email. This is the google email.
     */
    private String email;

    /**
     * The user's role. 0 for normal user, 1 for super user (admin)
     */
    private int role;

    /**
     * The date that the user was created
     */
    private String created;

    /**
     * The date that the user was last updated
     */
    private String lastUpdated;

    public UserPOJO(Long userId, String googleId, String fullName, String displayName, String givenName, String familyName, String imageURL, String email,
                    int role, String created, String lastUpdated) {
        this.userId = userId;
        this.googleId = googleId;
        this.fullName = fullName;
        this.displayName = displayName;
        this.givenName = givenName;
        this.familyName = familyName;
        this.imageURL = imageURL;
        this.email = email;
        this.role = role;
        this.created = created;
        this.lastUpdated = lastUpdated;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = fullName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return "UserPOJO{" +
                "\n\tuserId=" + userId +
                ",\n\tgoogleId='" + googleId + '\'' +
                ",\n\tfullName='" + fullName + '\'' +
                ",\n\tdisplayName='" + displayName + '\'' +
                ",\n\tgivenName='" + givenName + '\'' +
                ",\n\tfamilyName='" + familyName + '\'' +
                ",\n\timageURL='" + imageURL + '\'' +
                ",\n\temail='" + email + '\'' +
                ",\n\trole=" + role +
                ",\n\tcreated=" + created +
                ",\n\tlastUpdated=" + lastUpdated +
                "\n}";
    }
}
