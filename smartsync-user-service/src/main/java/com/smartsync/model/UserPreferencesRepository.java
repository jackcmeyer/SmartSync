package com.smartsync.model;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The User Preferences Repository
 */
public interface UserPreferencesRepository extends JpaRepository<UserPreferences, Long> {

    /**
     * Find's preferences by id
     * @param id the id to find by
     * @return the user preferences
     */
    UserPreferences findById(Long id);

    /**
     * Find's preferences by a user id
     * @param userId the user id to find by
     * @return the user preferences
     */
    UserPreferences findByUserId(Long userId);

}
