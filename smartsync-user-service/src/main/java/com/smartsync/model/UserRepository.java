package com.smartsync.model;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com
 */
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * Finds a user by their id
     * @param userId the user id to find by
     */
    User findByUserId(Long userId);

    /**
     * Find a user by their email
     * @param email the email to find by
     */
    User findByEmail(String email);

    /**
     * Find a user by their google id
     *
     * @param googleId the googleId to find by
     */
    User findByGoogleId(String googleId);

}
