package com.smartsync.model;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jack on 3/2/17.
 */
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * Finds a user by their id
     * @param userId the user id to find by
     */
    User findByUserId(String userId);

    /**
     * Find a user by their email
     * @param email the email to find by
     */
    User findByEmail(String email);
}
