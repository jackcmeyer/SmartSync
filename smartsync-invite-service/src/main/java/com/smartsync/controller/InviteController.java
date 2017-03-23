package com.smartsync.controller;

import com.smartsync.dto.InviteDTO;
import com.smartsync.error.*;
import com.smartsync.model.Invite;
import com.smartsync.service.InviteService;
import com.smartsync.validator.InviteValidator;
import com.smartsync.validator.ValidationError;
import com.smartsync.validator.ValidationErrorBuilder;
import communication.HouseholdServiceCommunication;
import communication.UserServiceCommunication;
import model.HouseholdPOJO;
import model.UserPOJO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The Invite Controller
 */
@RestController
public class InviteController {

    private final Logger logger = Logger.getLogger(this.getClass());


    @Autowired
    private InviteService inviteService;

    public InviteController() {

    }

    /**
     * Returns all of the invites
     * @return the response entity with all of the invites
     */
    @RequestMapping(method = RequestMethod.GET, value = "/", produces = "application/json")
    public ResponseEntity getAllInvites() {
        return ResponseEntity.ok(this.inviteService.getAllInvites());
    }

    /**
     * Gets an invite by id
     * @param id the id to find by
     * @return the invite
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = "application/json")
    public ResponseEntity getInvite(@PathVariable(value = "id") Long id) {

        Invite invite = this.inviteService.getInvite(id);

        if(invite == null) {
            String message = "Could not find invite with id: " + id;
            String url = "/invites/" + id;

            logger.error(message);
            throw new InviteNotFoundException(message, url);
        }

        logger.info("Successfully found invite: " + invite);

        return ResponseEntity.ok(invite);
    }

    /**
     * Aceepts an invite
     * @param id the id of the invite to accept
     * @return the invite that was accepted
     */
    @RequestMapping(method = RequestMethod.POST, value = "/{id}/accept", produces = "application/json")
    public ResponseEntity acceptInvite(@PathVariable(value = "id") Long id) {
        Invite invite = this.inviteService.getInvite(id);

        if(invite == null) {
            String message = "Could not find invite with id: " + id;
            String url = "/invites/" + id;

            logger.error(message);
            throw new InviteNotFoundException(message, url);
        }

        invite = this.inviteService.acceptInvite(id);

        return ResponseEntity.ok(invite);
    }


    /**
     * Adds a new invite
     * @param inviteDTO the invite data transfer object
     * @return the invite that was created
     */
    @RequestMapping(method = RequestMethod.POST, value = "/", produces = "application/json")
    public ResponseEntity addInvite(@RequestBody InviteDTO inviteDTO, Errors errors) {

        InviteValidator inviteValidator = new InviteValidator();
        inviteValidator.validate(inviteDTO, errors);

        ValidationError validationError = ValidationErrorBuilder.fromBindErrors(errors);


        // check for error in the request
        if(errors.hasErrors()) {

            logger.error("Invite could not be created: " + validationError.getErrors());
            throw new IllegalRequestFormatException("Could not add invite.", "/invites/", validationError);
        }

        // check if the uesr exists
        UserServiceCommunication userServiceCommunication = new UserServiceCommunication();
        UserPOJO userPOJO = userServiceCommunication.getUser(inviteDTO.getUserId());
        if(userPOJO == null) {
            String message = "Could not find user with id: " + inviteDTO.getUserId();
            String url = "/invites/";

            logger.error(message);
            throw new UserNotFoundException(message, url);
        }

        // check if the household exists
        HouseholdServiceCommunication householdServiceCommunication = new HouseholdServiceCommunication();
        HouseholdPOJO householdPOJO = householdServiceCommunication.getHouseholdByHouseholdId(inviteDTO.getHouseholdId());
        if(householdPOJO == null) {
            String message = "Could not find household with id: " + inviteDTO.getHouseholdId();
            String url = "/invites/";

            logger.error(message);
            throw new UserNotFoundException(message, url);
        }

        Invite invite = this.inviteService.addInvite(inviteDTO);

        return ResponseEntity.ok(invite);
    }

    /**
     * Deletes an invite by id
     * @param id the id to delete
     * @return the deleted invite
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", produces = "application/json")
    public ResponseEntity deleteInvite(@PathVariable("id") Long id) {
        Invite invite = this.inviteService.deleteInvite(id);

        if(invite == null) {
            String message = "Could not find invite with id: " + id;
            String url = "/invites/" + id;

            logger.error(message);
            throw new InviteNotFoundException(message, url);
        }

        logger.info("Successfully deleted invite: " + invite);
        return ResponseEntity.ok(invite);
    }

    /**
     * Returns all of the invites for a user
     * @param userId the user to find invites for
     * @return the response entity with all of the invites for user
     */
    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}", produces = "application/json")
    public ResponseEntity getAllInvitesForUser(@PathVariable("userId") Long userId) {

        UserServiceCommunication userServiceCommunication = new UserServiceCommunication();
        UserPOJO userPOJO = userServiceCommunication.getUser(userId);
        if(userPOJO == null) {
            String message = "Could not find user with id: " + userId;
            String url = "/invites/users/" + userId;

            logger.error(message);
            throw new UserNotFoundException(message, url);
        }


        List<Invite> invites = this.inviteService.getAllInvitesForUser(userId);

        return ResponseEntity.ok(invites);
    }

    /**
     * Returns all of the invites for a household
     * @param householdId the household to find invites for
     * @return the response entity with all of the invites for household
     */
    @RequestMapping(method = RequestMethod.GET, value = "/households/{householdId}", produces = "application/json")
    public ResponseEntity getAllInvitesForHousehold(@PathVariable("householdId") Long householdId) {

        HouseholdServiceCommunication householdServiceCommunication = new HouseholdServiceCommunication();
        HouseholdPOJO householdPOJO = householdServiceCommunication.getHouseholdByHouseholdId(householdId);
        if(householdPOJO == null) {
            String message = "Could not find household with id: " + householdId;
            String url = "/invites/households" + householdId;

            logger.error(message);
            throw new HouseholdNotFoundException(message, url);
        }


        List<Invite> invites = this.inviteService.getAllInvitesForHousehold(householdId);

        return ResponseEntity.ok(invites);
    }

    /**
     * Deletes all invites for a user
     * @param userId the user to delete invites for
     * @return the response entity with all of the deleted invites
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{userId}", produces = "application/json")
    public ResponseEntity deleteAllInvitesForUser(@PathVariable("userId") Long userId) {

        UserServiceCommunication userServiceCommunication = new UserServiceCommunication();
        UserPOJO userPOJO = userServiceCommunication.getUser(userId);
        if(userPOJO == null) {
            String message = "Could not find user with id: " + userId;
            String url = "/invites/users/" + userId;

            logger.error(message);
            throw new UserNotFoundException(message, url);
        }


        List<Invite> invites = this.inviteService.deleteAllInvitesForUser(userId);

        return ResponseEntity.ok(invites);
    }

    /**
     * Deletes all invites for household
     * @param householdId the household to delete invites for
     * @return the response entity with all of the deleted invites
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/households/{householdId}", produces = "application/json")
    public ResponseEntity deleteAllInvitesForHousehold(@PathVariable("householdId") Long householdId) {

        HouseholdServiceCommunication householdServiceCommunication = new HouseholdServiceCommunication();
        HouseholdPOJO householdPOJO = householdServiceCommunication.getHouseholdByHouseholdId(householdId);
        if(householdPOJO == null) {
            String message = "Could not find household with id: " + householdId;
            String url = "/invites/households" + householdId;

            logger.error(message);
            throw new HouseholdNotFoundException(message, url);
        }

        List<Invite> invites = this.inviteService.deleteAllInvitesForHousehold(householdId);

        return ResponseEntity.ok(invites);
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
     * Handles the invite not found exception
     * @param e the invite not found exception
     * @return the response entity with the error
     */
    @ExceptionHandler(value = InviteNotFoundException.class)
    public ResponseEntity handleInviteNotFoundException(UserNotFoundException e) {
        ErrorInfo error = new ErrorInfo("Invite Not Found", e.getMessage(), e.getUrl());

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
