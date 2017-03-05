package com.smartsync.model;

import com.smartsync.dto.UserDTO;

import javax.persistence.Entity;
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
     * The user id for the user. This is also the id for the user's google account
     */
    @Id
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
     * @param userId
     * @param fullName
     * @param givenName
     * @param familyName
     * @param imageURL
     * @param email
     * @param role
     */
    public User(String userId, String fullName, String givenName, String familyName, String imageURL, String email,
                int role) {
        this.userId = userId;
        this.fullName = fullName;
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
        this.userId = userDTO.getUserId();
        this.fullName = userDTO.getFullName();
        this.givenName = userDTO.getGivenName();
        this.familyName = userDTO.getFamilyName();
        this.imageURL = userDTO.getImageURL();
        this.email = userDTO.getEmail();
        this.role = Integer.parseInt(userDTO.getRole());
        this.created = new Date();
        this.lastUpdated = new Date();
    }

    public User() {
        // empty constructor
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
