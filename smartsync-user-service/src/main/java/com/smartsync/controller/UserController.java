package com.smartsync.controller;

import com.smartsync.dto.UserDTO;
import com.smartsync.error.*;
import com.smartsync.model.User;
import com.smartsync.service.UserService;
import com.smartsync.validator.UserValidator;
import com.smartsync.validator.ValidationError;
import com.smartsync.validator.ValidationErrorBuilder;
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
    public ResponseEntity getUserById(@PathVariable("id") String id) {

        User user = this.userService.getUserById(id);

        if(user == null) {

            String message = "Could not find user with id " + id + ".";
            String url = "/users/" + id;

            throw new UserNotFoundException(message, url);
        }

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

        UserValidator userValidator = new UserValidator();
        userValidator.validate(userDTO, errors);

        ValidationError validationError = ValidationErrorBuilder.fromBindErrors(errors);


        if(errors.hasErrors()) {
            throw new IllegalRequestFormatException("Could not add user.", "/users/", validationError);
        }

        if(this.userService.getUserById(userDTO.getUserId()) != null) {

            throw new DuplicateUserException("Could not add new user with id " + userDTO.getUserId() +
                    " because a user with this id already exists.", "/users/");
        }

        if(this.userService.getUserByEmail(userDTO.getEmail()) != null) {
            throw new DuplicateUserException("Could not add new user with email " + userDTO.getEmail() +
                    " because a user with this id already exists.", "/users/");
        }


        User user = new User(userDTO);
        User savedUser = this.userService.addUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/", produces = "application/json")
    public ResponseEntity updateUser(@RequestBody UserDTO userDTO, Errors errors) {

        UserValidator userValidator = new UserValidator();
        userValidator.validate(userDTO, errors);

        ValidationError validationError = ValidationErrorBuilder.fromBindErrors(errors);

        if(errors.hasErrors()) {
            throw new IllegalRequestFormatException("Could not update user.", "/users/", validationError);
        }

        User updatedUser = this.userService.updateUser(new User(userDTO));

        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Deletes a user by id
     * @param id the user id
     * @return the user that was deleted
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", produces = "application/json")
    public ResponseEntity deleteUser(@PathVariable("id") String id) {

        User user = this.userService.deleteUser(id);

        if(user == null) {
            String message = "Could not delete user with id: " + id;
            String url = "/users/" + id;
            throw new UserNotFoundException(message, url);
        }

        return ResponseEntity.ok(user);
    }


    /**
     * Handles the user not found exception
     * @param e the user not found exception
     * @return the response entity with the error
     */
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity handleUserNotFoundException(UserNotFoundException e) {
        ErrorInfo error = new ErrorInfo("User Not Found Exception", e.getMessage(), e.getUrl());

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
