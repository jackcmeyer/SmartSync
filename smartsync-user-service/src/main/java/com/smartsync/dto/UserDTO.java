package com.smartsync.dto;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by jack on 3/5/17.
 */
public class UserDTO {

    /**
     * The user id for the user. This is also the id for the user's google account
     */
    private String userId;

    /**
     * The user's full name
     */
    private String fullName;

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
    private String role;

    public UserDTO(String userId, String fullName, String givenName, String familyName, String imageURL, String email,
                   String role) {
        this.userId = userId;
        this.fullName = fullName;
        this.givenName = givenName;
        this.familyName = familyName;
        this.imageURL = imageURL;
        this.email = email;
        this.role = role;
    }

    public UserDTO() {
        // default constructor
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDTO {" +
                "\n\tuserId='" + userId + '\'' +
                ", \n\tfullName='" + fullName + '\'' +
                ", \n\tgivenName='" + givenName + '\'' +
                ", \n\tfamilyName='" + familyName + '\'' +
                ", \n\timageURL='" + imageURL + '\'' +
                ", \n\temail='" + email + '\'' +
                ", \n\trole=" + role +
                "\n}";
    }
}
