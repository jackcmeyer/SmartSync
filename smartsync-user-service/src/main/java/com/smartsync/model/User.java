package com.smartsync.model;

import com.smartsync.dto.UserDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The User Model
 */
@Entity
public class User {

    /**
     * The user id for the user.
     */
    @Id @GeneratedValue
    private Long userId;

    /**
     * The user's id for their google account
     */
    private String googleId;

    /**
     * The user's display name, similar to a username
     */
    private String displayName;

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
    private int role;

    /**
     * The date that the user was created
     */
    private Date created;

    /**
     * The date that the user was last updated
     */
    private Date lastUpdated;


    /**
     * Constructor which uses parameters to create the new user
     * @param googleId
     * @param fullName
     * @param givenName
     * @param familyName
     * @param imageURL
     * @param email
     * @param role
     */
    public User(String googleId, String fullName, String displayName, String givenName, String familyName, String imageURL, String email,
                int role) {
        this.googleId = googleId;
        this.fullName = fullName;
        this.displayName = displayName;
        this.givenName = givenName;
        this.familyName = familyName;
        this.imageURL = imageURL;
        this.email = email;
        this.role = role;
        this.created = new Date();
        this.lastUpdated = new Date();
    }

    /**
     * Constructor which users the DTO to create the new user
     * @param userDTO
     */
    public User(UserDTO userDTO) {
        this.googleId = userDTO.getGoogleId();
        this.fullName = userDTO.getFullName();
        this.displayName = userDTO.getDisplayName();
        this.givenName = userDTO.getGivenName();
        this.familyName = userDTO.getFamilyName();
        this.imageURL = userDTO.getImageURL();
        this.email = userDTO.getEmail();
        this.role = userDTO.getRole();
        this.created = new Date();
        this.lastUpdated = new Date();
    }

    public User() {
        // empty constructor
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
