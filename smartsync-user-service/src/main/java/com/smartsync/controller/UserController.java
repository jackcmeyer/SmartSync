package com.smartsync.controller;

import com.smartsync.dto.UpdateUserDTO;
import com.smartsync.dto.UserDTO;
import com.smartsync.error.*;
import com.smartsync.model.User;
import com.smartsync.service.UserService;
import com.smartsync.validator.UpdateUserValidator;
import com.smartsync.validator.UserValidator;
import com.smartsync.validator.ValidationError;
import com.smartsync.validator.ValidationErrorBuilder;
import model.HouseholdPOJO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jack Meyer
 *
 * The User Controller
 */
@RestController
public class UserController {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    public UserController() {

    }

    /**
     * Gets all users.
     * @return all of the users
     */
    @RequestMapping(method = RequestMethod.GET, value = "/", produces = "application/json")
    public List<User> getAllUsers() {
        return this.userService.getAllUsers();
    }

    /**
     * Gets user by id
     *
     * @param id gets the user by id
     *
     * @return the user
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = "application/json")
    public ResponseEntity getUserById(@PathVariable("id") Long id) {

        logger.info("Getting user information for id: " + id);

        User user = this.userService.getUserById(id);

        if(user == null) {

            String message = "Could not find user with id " + id + ".";
            String url = "/users/" + id;

            logger.error(message);
            throw new UserNotFoundException(message, url);
        }

        logger.info("Successfully got user information: " + user);
        return ResponseEntity.ok(user);
    }

    /**
     * Gets a user by their email
     * @param email the email to find by
     * @return the response entity with the user information
     */
    @RequestMapping(method = RequestMethod.GET, value = "/email/{email}/", produces = "application/json")
    public ResponseEntity getUserByEmail(@PathVariable("email") String email) {
        
        User user = this.userService.getUserByEmail(email);

        if(user == null) {

            String message = "Could not find user with email " + email + ".";
            String url = "/users/email/" + email;

            logger.error(message);
            throw new UserNotFoundException(message, url);
        }

        logger.info("Successfully got user information: " + user);
        return ResponseEntity.ok(user);
    }


    /**
     * Adds a new user to the database
     *
     * @param userDTO the user dto to add
     * @param errors the errors list
     *
     * @return the user that was addded
     */
    @RequestMapping(method = RequestMethod.POST, value = "/", produces = "application/json")
    public ResponseEntity addUser(@RequestBody UserDTO userDTO, Errors errors) {

        logger.info("Adding new user: " + userDTO);

        UserValidator userValidator = new UserValidator();
        userValidator.validate(userDTO, errors);

        ValidationError validationError = ValidationErrorBuilder.fromBindErrors(errors);


        if(errors.hasErrors()) {

            logger.error("User could not be created: " + validationError.getErrors());
            throw new IllegalRequestFormatException("Could not add user.", "/users/", validationError);
        }




        if(this.userService.getUserByGoogleId(userDTO.getGoogleId()) != null) {

            logger.error("Could not create new user with google id : " + userDTO.getGoogleId() +
                    " because user already exists");
            throw new DuplicateUserException("Could not add new user with google id " + userDTO.getGoogleId() +
                    " because a user with this id already exists.", "/users/");
        }

        if(this.userService.getUserByEmail(userDTO.getEmail()) != null) {

            logger.error("Could not create new use with email: " + userDTO.getEmail() + " because user already exists");
            throw new DuplicateUserException("Could not add new user with email " + userDTO.getEmail() +
                    " because a user with this id already exists.", "/users/");
        }



        User user = new User(userDTO);
        User savedUser = this.userService.addUser(user);
        logger.info("Successfully created new user: " + savedUser);

        return ResponseEntity.ok(savedUser);
    }

    /**
     * Updates the user information
     * @param updateUserDTO the user information to update
     * @param errors an error container
     * @return the udpated user
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/", produces = "application/json")
    public ResponseEntity updateUser(@RequestBody UpdateUserDTO updateUserDTO, Errors errors) {

        UpdateUserValidator userValidator = new UpdateUserValidator();
        userValidator.validate(updateUserDTO, errors);

        ValidationError validationError = ValidationErrorBuilder.fromBindErrors(errors);

        if(errors.hasErrors()) {
            throw new IllegalRequestFormatException("Could not update user.", "/users", validationError);
        }

        User updatedUser = this.userService.updateUser(updateUserDTO);

        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Deletes a user by id
     * @param id the user id
     * @return the user that was deleted
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", produces = "application/json")
    public ResponseEntity deleteUser(@PathVariable("id") Long id) {

        User user = this.userService.deleteUser(id);

        if(user == null) {
            String message = "Could not delete user with id: " + id;
            String url = "/users/" + id;

            logger.error("Could not find user with id: " + id + " to delete");
            throw new UserNotFoundException(message, url);
        }


        logger.info("Successfully deleted user: " + user);
        return ResponseEntity.ok(user);
    }

    /**
     * Gets the household which the user is a part of
     * @param id the user id
     * @return the household which the uesr is a part of
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/household", produces = "application/json")
    public ResponseEntity getHouseholdForUser(@PathVariable("id") Long id) {

        User user = this.userService.getUserById(id);


        if(user == null) {
            String message = "Could not find user with id " + id + ".";
            String url = "/users/" + id + "/households";

            logger.error(message);
            throw new UserNotFoundException(message, url);
        }

        HouseholdPOJO household = this.userService.getHouseholdForUserId(id);

        if(household == null) {
            String message = "Could not find household for user with id " + id + ".";
            String url = "/users/" + id + "household";

            logger.error(message);
            throw new HouseholdNotFoundException(message, url);
        }

        logger.info("Successfully found household " + household);

        return ResponseEntity.ok(household);
    }

    /**
     * Gets the user with the google id
     * @param googleId the google id to find by
     * @return the user with the google id
     */
    @RequestMapping(method = RequestMethod.GET, value = "/google/{id}")
    public ResponseEntity getUserFromGoogleId(@PathVariable("id") String googleId) {

        User user = this.userService.getUserFromGoogleId(googleId);

        if(user == null) {
            String message = "Could not find user with google id " + googleId + ".";
            String url = "/users/google/" + googleId;

            logger.error(message);
            throw new UserNotFoundException(message, url);
        }

        return ResponseEntity.ok(user);
    }

    /**
     * Gets the user with the google id
     * @param googleId the google id to find by
     * @return the user with the google id
     */
    @RequestMapping(method = RequestMethod.GET, value = "/google/{id}/household")
    public ResponseEntity getUserHouseholdFromGoogleId(@PathVariable("id") String googleId) {

        User user = this.userService.getUserByGoogleId(googleId);

        if(user == null) {
            String message = "Could not find user with googleId " + googleId + ".";
            String url = "/users/" + googleId + "/households";

            logger.error(message);
            throw new UserNotFoundException(message, url);
        }

        HouseholdPOJO household = this.userService.getHouseholdForUserId(user.getUserId());

        if(household == null) {
            String message = "Could not find household for user with id " + user.getUserId() + ".";
            String url = "/users/" + user.getUserId() + "household";

            logger.error(message);
            throw new HouseholdNotFoundException(message, url);
        }

        logger.info("Successfully found household " + household);

        return ResponseEntity.ok(household);
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
     * Handles the duplicate user exception
     * @param e the duplicate user exception
     * @return the response entity with the error
     */
    @ExceptionHandler(value = DuplicateUserException.class)
    public ResponseEntity handleDuplicateUserException(DuplicateUserException e) {
        ErrorInfo error = new ErrorInfo("Duplicate User", e.getMessage(), e.getUrl());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * Handles the household not found exception
     * @param e the household not found exception
     * @return the response entity with the error
     */
    @ExceptionHandler(value = HouseholdNotFoundException.class)
    public ResponseEntity handleHouseholdNotFoundException(HouseholdNotFoundException e) {
        ErrorInfo error = new ErrorInfo("Household Not Found", e.getMessage(), e.getUrl());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
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
