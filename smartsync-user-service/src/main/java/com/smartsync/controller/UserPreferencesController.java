package com.smartsync.controller;

import com.smartsync.dto.UpdateUserPreferencesDTO;
import com.smartsync.dto.UserPreferencesDTO;
import com.smartsync.error.*;
import com.smartsync.model.User;
import com.smartsync.model.UserPreferences;
import com.smartsync.service.UserPreferencesService;
import com.smartsync.service.UserService;
import com.smartsync.validator.UpdateUserPreferencesValidator;
import com.smartsync.validator.UserPreferencesValidator;
import com.smartsync.validator.ValidationError;
import com.smartsync.validator.ValidationErrorBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The user preferences controller
 */
@RestController
public class UserPreferencesController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserPreferencesService userPreferencesService;

    public UserPreferencesController() {

    }

    /**
     * Gets all preferences
     * @return response entity with the list of preferences
     */
    @RequestMapping(method = RequestMethod.GET, value = "/preferences", produces = "application/json")
    public ResponseEntity getAllUserPreferences() {

        return ResponseEntity.ok(this.userPreferencesService.getAllUserPreferences());
    }

    /**
     * Gets user preferences by id
     * @param id the id to find by
     * @return the response entity with the user preferences
     */
    @RequestMapping(method = RequestMethod.GET, value = "/preferences/{id}", produces = "application/json")
    public ResponseEntity getUserPreferencesById(@PathVariable("id") Long id) {
        UserPreferences userPreferences = this.userPreferencesService.getUserPreferencesById(id);

        // check if the user preferences exists
        if(userPreferences == null) {
            String message = "Could not find user preferences with id " + id;
            String url = "/users/preferences/" + id;

            throw new UserPreferencesNotFoundException(message, url);
        }

        return ResponseEntity.ok(userPreferences);
    }

    /**
     * Gets user preferences by user id
     * @param userId the user id to find by
     * @return the user preferences for a user
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{userId}/preferences", produces = "application/json")
    public ResponseEntity getUserPreferencesByUserId(@PathVariable("userId") Long userId) {

        // check if the user exists
        User user = this.userService.getUserById(userId);
        if(user == null) {
            String message = "Could not find user with id " + userId;
            String url = "/users/" + userId + "/preferences";

            throw new UserNotFoundException(message, url);
        }

        UserPreferences userPreferences = this.userPreferencesService.getUserPreferencesByUserId(userId);
        // check if the user preferences exists
        if(userPreferences == null) {
            String message = "Could not find user preferences for user with id " + userId;
            String url = "/users/" + userId + "/preferences";

            throw new UserPreferencesNotFoundException(message, url);
        }

        return ResponseEntity.ok(userPreferences);
    }

    /**
     * Adds a new user preference for a user
     * @param userPreferencesDTO the user preference dto
     * @return the response with the new user preference
     */
    @RequestMapping(method = RequestMethod.POST, value = "/preferences", produces = "application/json")
    public ResponseEntity addUserPreferences(@RequestBody UserPreferencesDTO userPreferencesDTO, Errors errors) {

        UserPreferencesValidator userPreferencesValidator = new UserPreferencesValidator();
        userPreferencesValidator.validate(userPreferencesDTO, errors);

        // check for errors
        if(errors.hasErrors()) {
            String message = "Could not add new user preferences";
            String url = "/preferences/";

            ValidationError validationError = ValidationErrorBuilder.fromBindErrors(errors);

            throw new IllegalRequestFormatException(message, url, validationError);
        }

        // check if the user exists
        User user = this.userService.getUserById(userPreferencesDTO.getUserId());
        if(user == null) {
            String message = "Could not find user with id " + userPreferencesDTO.getUserId();
            String url = "/preferences/";

            throw new UserNotFoundException(message, url);
        }

        UserPreferences userPreferences = this.userPreferencesService.addUserPreferences(userPreferencesDTO);
        return ResponseEntity.ok(userPreferences);
    }

    /**
     * Updates the user preferences
     * @param updateUserPreferencesDTO the update preferences dto
     * @param errors the errors list
     * @return the respones entity with the updated preference
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/preferences", produces = "application/json")
    public ResponseEntity updateUserPreferences(@RequestBody UpdateUserPreferencesDTO updateUserPreferencesDTO, Errors errors) {
        UpdateUserPreferencesValidator updateUserPreferencesValidator = new UpdateUserPreferencesValidator();
        updateUserPreferencesValidator.validate(updateUserPreferencesDTO, errors);

        // check for errors
        if(errors.hasErrors()) {
            String message = "Could not update user preferences";
            String url = "/preferences/";

            ValidationError validationError = ValidationErrorBuilder.fromBindErrors(errors);

            throw new IllegalRequestFormatException(message, url, validationError);
        }

        // check if the user preferences exist
        UserPreferences userPreferences = this.userPreferencesService.getUserPreferencesById(updateUserPreferencesDTO.getId());
        if(userPreferences == null) {
            String message = "Could not find user preferences with id " + updateUserPreferencesDTO.getId();
            String url = "/users/preferences/" + updateUserPreferencesDTO.getId();

            throw new UserPreferencesNotFoundException(message, url);
        }

        // check if the user exists
        User user = this.userService.getUserById(updateUserPreferencesDTO.getUserId());
        if(user == null) {
            String message = "Could not find user with id " + updateUserPreferencesDTO.getUserId();
            String url = "users/preferences/";

            throw new UserNotFoundException(message, url);
        }

        UserPreferences updatedUserPreferences = this.userPreferencesService.updateUserPreferences(updateUserPreferencesDTO);
        return ResponseEntity.ok(updatedUserPreferences);
    }

    /**
     * Deletes a user preferences by id
     * @param id the id to delete by
     * @return the response entity with the deleted user preferences
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/preferences/{id}", produces = "application/json")
    public ResponseEntity deleteUserPreferences(@PathVariable("id") Long id) {

        // check if the user preferences exist
        UserPreferences userPreferences = this.userPreferencesService.getUserPreferencesById(id);
        if(userPreferences == null) {
            String message = "Could not find user preferences with id " + id;
            String url = "users/preferences/" + id;

            throw new UserPreferencesNotFoundException(message, url);
        }

        UserPreferences deletedUserPreferences = this.userPreferencesService.deleteUserPreferences(id);
        return ResponseEntity.ok(deletedUserPreferences);
    }

    /**
     * Handles the user not found exception
     * @param e the user not found exception
     * @return the response entity with the error
     */
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity handleUserNotFoundException(UserNotFoundException e) {
        ErrorInfo error = new ErrorInfo("User Not Found", e.getMessage(), e.getUrl());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    /**
     * Handles the user preferences not found exception
     * @param e the user preferences not found exception
     * @return the response entity with the error
     */
    @ExceptionHandler(value = UserPreferencesNotFoundException.class)
    public ResponseEntity handleUserPreferencesNotFoundException(UserPreferencesNotFoundException e) {
        ErrorInfo error = new ErrorInfo("User Not Found", e.getMessage(), e.getUrl());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    /**
     * Handles when there is an illegal request format exception. This includes missing parameters, improper input,
     * and other bad requests.
     * @param e the illegal request format exception
     * @return the response entity with the error
     */
    @ExceptionHandler(value = IllegalRequestFormatException.class)
    public ResponseEntity handleIllegalRequestFormatException(IllegalRequestFormatException e) {
        IllegalRequestFormatErrorInfo error = new IllegalRequestFormatErrorInfo("Illegal Request Format",
                e.getMessage(), e.getUrl(), e.getErrors());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
