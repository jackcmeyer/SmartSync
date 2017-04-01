package com.smartsync.service;

import com.smartsync.dto.UpdateUserPreferencesDTO;
import com.smartsync.dto.UserPreferencesDTO;
import com.smartsync.model.UserPreferences;
import com.smartsync.model.UserPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The User Preferences Service
 */
@Component
public class UserPreferencesService {

    @Autowired
    private UserPreferencesRepository userPreferencesRepository;


    public UserPreferencesService() {

    }

    /**
     * Gets all user preferences in the database
     * @return the list of user preferences
     */
    public List<UserPreferences> getAllUserPreferences() {
        return this.userPreferencesRepository.findAll();
    }

    /**
     * Gets user preferences by id
     * @param id the id to find by
     * @return the user preferences
     */
    public UserPreferences getUserPreferencesById(Long id) {
        return this.userPreferencesRepository.findById(id);
    }

    /**
     * Gets user preferences by user id
     * @param userId the user id to find by
     * @return the user preferences
     */
    public UserPreferences getUserPreferencesByUserId(Long userId) {
        return this.userPreferencesRepository.findByUserId(userId);
    }

    /**
     * Adds a new user preferences
     * @param userPreferencesDTO the user preferences dto
     * @return the new user preferences
     */
    public UserPreferences addUserPreferences(UserPreferencesDTO userPreferencesDTO) {

        UserPreferences userPreferences = new UserPreferences(userPreferencesDTO);
        this.userPreferencesRepository.save(userPreferences);

        return userPreferences;
    }

    /**
     * Updates the user preferences
     * @return the update user preferences
     */
    public UserPreferences updateUserPreferences(UpdateUserPreferencesDTO updateUserPreferencesDTO) {
        UserPreferences userPreferences = this.userPreferencesRepository.findById(updateUserPreferencesDTO.getId());
        userPreferences.setName(updateUserPreferencesDTO.getName());
        userPreferences.setPrimaryColor(updateUserPreferencesDTO.getPrimaryColor());
        userPreferences.setAccentColor(updateUserPreferencesDTO.getAccentColor());
        userPreferences.setNeutralLightColor(updateUserPreferencesDTO.getNeutralLightColor());
        userPreferences.setNeutralDarkColor(updateUserPreferencesDTO.getNeutralDarkColor());

        UserPreferences updatedUserPreferences = this.userPreferencesRepository.save(userPreferences);

        return updatedUserPreferences;
    }

    /**
     * Deletes user preferences by id
     * @param id the id to delete by
     * @return the deleted user preferences
     */
    public UserPreferences deleteUserPreferences(Long id) {

        UserPreferences userPreferences = this.userPreferencesRepository.findById(id);
        this.userPreferencesRepository.delete(userPreferences);

        return userPreferences;
    }
}
