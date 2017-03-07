package com.smartsync.service;

import com.smartsync.model.User;
import com.smartsync.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Date;
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

    public UserService() {

    }

    /**
     * Get a user by their id
     * @param userId the user Id
     * @return
     */
    public User getUserById(String userId) {
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
        return savedUser;
    }

    /**
     * Updates the user
     * @param user the user to udate
     * @return the new user
     */
    @Transactional
    public User updateUser(User user) {

        User savedUser = this.userRepository.findByUserId(user.getUserId());




        savedUser.setEmail(user.getEmail());
        savedUser.setFamilyName(user.getFamilyName());
        savedUser.setFullName(user.getFullName());
        savedUser.setGivenName(user.getFamilyName());
        savedUser.setImageURL(user.getImageURL());
        savedUser.setRole(user.getRole());
        savedUser.setUserId(user.getUserId());

        savedUser.setLastUpdated(new Date());

        return savedUser;

    }

    /**
     * Deletes a user from the database
     *
     * @param id the id to delete
     *
     * @return the deleted user
     */
    public User deleteUser(String id) {
        User deletedUser = this.userRepository.findByUserId(id);

        if(deletedUser == null) {
            return null;
        }

        else {
            this.userRepository.delete(id);

            return deletedUser;
        }


    }

}
