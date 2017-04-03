package com.smartsync.dto;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The update user dto
 */
public class UpdateUserDTO {
    /**
     * The user id for the user.
     */
    private Long userId;

    /**
     * The user id for the user. This is also the id for the user's google account
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

    public UpdateUserDTO(String googleId, String fullName, String displayName, String givenName, String familyName, String imageURL, String email,
                   int role) {
        this.googleId = googleId;
        this.fullName = fullName;
        this.displayName = displayName;
        this.givenName = givenName;
        this.familyName = familyName;
        this.imageURL = imageURL;
        this.email = email;
        this.role = role;
    }

    public UpdateUserDTO() {
        // default constructor
    }

    public Long getUserId() {
        return userId;
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
        this.displayName = displayName;
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
}
