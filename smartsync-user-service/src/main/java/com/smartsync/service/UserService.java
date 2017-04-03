package com.smartsync.service;

import com.smartsync.dto.UpdateUserDTO;
import com.smartsync.dto.UserPreferencesDTO;
import com.smartsync.model.User;
import com.smartsync.model.UserRepository;
import communication.HouseholdServiceCommunication;
import model.HouseholdPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The User Service
 */
@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPreferencesService userPreferencesService;

    public UserService() {

    }

    /**
     * Get a user by their id
     * @param userId the user Id
     * @return
     */
    public User getUserById(Long userId) {
        return userRepository.findByUserId(userId);
    }

    /**
     * Gets a user by their email
     * @param email the email
     * @return
     */
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Gets a user by their google id
     * @param googleId the google id
     * @return the user
     */
    public User getUserByGoogleId(String googleId) {
        return userRepository.findByGoogleId(googleId);
    }


    /**
     * Gets all of the user's in the database
     * @return all of the user's
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Adds a user to the database
     * @param user the user to add
     * @return the new user that is saved
     */
    public User addUser(User user) {
        User savedUser = this.userRepository.save(user);

        UserPreferencesDTO userPreferencesDTO = new UserPreferencesDTO(savedUser.getUserId(),
                "Default Preferences", "#3b5156", "#00ffab", "#969696", "#313131", "jack");
        this.userPreferencesService.addUserPreferences(userPreferencesDTO);

        return savedUser;
    }

    /**
     * Updates the user
     * @param user the user to udate
     * @return the new user
     */
    public User updateUser(UpdateUserDTO user) {

        User savedUser = this.userRepository.findByUserId(user.getUserId());

        savedUser.setEmail(user.getEmail());
        savedUser.setFamilyName(user.getFamilyName());
        savedUser.setDisplayName(user.getDisplayName());
        savedUser.setFullName(user.getFullName());
        savedUser.setGivenName(user.getFamilyName());
        savedUser.setImageURL(user.getImageURL());
        savedUser.setRole(user.getRole());
        savedUser.setLastUpdated(new Date());

        this.userRepository.save(savedUser);

        return savedUser;

    }

    /**
     * Deletes a user from the database
     *
     * @param id the id to delete
     *
     * @return the deleted user
     */
    public User deleteUser(Long id) {
        User deletedUser = this.userRepository.findByUserId(id);

        if(deletedUser == null) {
            return null;
        }

        else {
            HouseholdServiceCommunication householdServiceCommunication = new HouseholdServiceCommunication();
            HouseholdPOJO householdPOJO = householdServiceCommunication.getHouseholdForUserId(id);

            if(householdPOJO != null) {
                HashMap<String, String> parameters = new HashMap<>();
                parameters.put("userId", Long.toString(id));
                parameters.put("householdId", Long.toString(householdPOJO.getHouseholdId()));

                householdServiceCommunication.removeUserFromHousehold(parameters);
            }

            this.userRepository.delete(deletedUser);


            return deletedUser;
        }
    }

    /**
     * Gets the household which the user belongs to
     * @param id the id of the user
     * @return the household which the user belongs to
     */
    public HouseholdPOJO getHouseholdForUserId(Long id) {
        HouseholdServiceCommunication householdServiceCommunication = new HouseholdServiceCommunication();
        return householdServiceCommunication.getHouseholdForUserId(id);
    }

    /**
     * Gets the user from the google id
     * @param id the google id to find by
     * @return the user
     */
    public User getUserFromGoogleId(String id) {
        User user = this.userRepository.findByGoogleId(id);

        return user;
    }
}
