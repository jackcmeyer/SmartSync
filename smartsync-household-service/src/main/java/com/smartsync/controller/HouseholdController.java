package com.smartsync.controller;

import com.smartsync.dto.HouseholdDTO;
import com.smartsync.error.*;
import com.smartsync.model.Household;
import com.smartsync.model.HouseholdUserLookup;
import com.smartsync.service.HouseholdService;
import com.smartsync.service.HouseholdUserLookupService;
import com.smartsync.validator.HouseholdValidator;
import com.smartsync.validator.ValidationError;
import com.smartsync.validator.ValidationErrorBuilder;
import communication.UserServiceCommunication;
import model.UserPOJO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jack Meyer
 *
 * The Household Controller
 */
@RestController
public class HouseholdController {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private HouseholdService householdService;

    @Autowired
    private HouseholdUserLookupService householdUserLookupService;

    public HouseholdController() {

    }

    /**
     * Gets all of the households in the database
     *
     * @return all of the households in the database
     */
    @RequestMapping(method = RequestMethod.GET, value = "/", produces = "application/json")
    public ResponseEntity getAllHouseholds() {

        List<Household> householdList = new ArrayList<>();

        logger.info("Successfully got all households: " + householdList);
        return ResponseEntity.ok(householdList);
    }

    /**
     * Gets the household by id
     *
     * @param id the id to get the household by
     *
     * @return the household
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = "application/json")
    public ResponseEntity getHouseHoldById(@PathVariable("id") Long id) {

        logger.info("Getting household information for id: " + id);

        Household h = this.householdService.getHouseHoldById(id);

        if(h == null) {
            String message = "Could not find household with id " + id + ".";
            String url = "households/{id}";


            logger.error(message);
            throw new HouseholdNotFoundException(message, url);
        }


        logger.info("Successfully got household information: " + h);
        return ResponseEntity.ok(h);
    }

    /**
     * Adds a new household
     *
     * @param householdDTO the data that the new household should be created with
     *
     * @return the household that was added
     */
    @RequestMapping(method = RequestMethod.POST, value = "/", produces = "application/json")
    public ResponseEntity addHousehold(@RequestBody HouseholdDTO householdDTO, Errors errors) {

        HouseholdValidator validator = new HouseholdValidator();
        validator.validate(householdDTO, errors);

        ValidationError validationError = ValidationErrorBuilder.fromBindErrors(errors);

        if(errors.hasErrors()) {
            String message = "Could not create new household.";
            String url = "/households/";

            logger.error("Could not create new household: " + errors);
            throw new IllegalRequestFormatException(message, url, validationError);
        }

        UserServiceCommunication userServiceCommunication = new UserServiceCommunication();
        UserPOJO user = userServiceCommunication.getUser(householdDTO.getOwnerId());

        if(user == null) {
            String message = "Could not create new household because owner with id: " + householdDTO.getOwnerId() +
                    " does not exist";
            String url = "/households";


            logger.error(message);
            throw new UserNotFoundException(message, url);
        }

        Household h = this.householdService.addHousehold(new Household(householdDTO));
        this.householdUserLookupService.addUserToHouseHold(h.getOwnerId(), h.getHouseholdId());

        logger.info("Successfully added new household: " + h);
        return ResponseEntity.ok(h);
    }

    /**
     * Deletes a household
     *
     * @param id the id that we are going to delete
     *
     * @return the household id
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", produces = "application/json")
    public ResponseEntity deleteHousehold(@PathVariable("id") Long id) {
        Household h = this.householdService.deleteHousehold(id);

        if(h == null) {
            String message = "Could not delete household because household with id " + id + " does not exist.";
            String url = "households/{id}";

            logger.error(message);
            throw new HouseholdNotFoundException(message, url);
        }

        logger.info("Successfully deleted household: " + h);
        return ResponseEntity.ok(h);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/users", produces = "application/json")
    public ResponseEntity getUsersInHousehold(@PathVariable("id") Long id) {

        List<UserPOJO> users = this.householdUserLookupService.getUsersInHousehold(id);

        return ResponseEntity.ok(users);

    }

    @RequestMapping(method = RequestMethod.POST, value = "/{id}/users/{userId}", produces = "application/json")
    public ResponseEntity addUserToHousehold(@PathVariable("id") Long id,
                                             @PathVariable("userId") Long userId) {

        HouseholdUserLookup householdUserLookup = this.householdUserLookupService.addUserToHouseHold(userId, id);
        return ResponseEntity.ok().body("success");

    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}", produces = "application/json")
    public ResponseEntity getHouseholdForUser(@PathVariable("userId") Long userId) {

        HouseholdUserLookup householdUserLookup = this.householdUserLookupService.getHouseholdForUser(userId);

        Household household = this.householdService.getHouseHoldById(householdUserLookup.getHouseholdId());

        return ResponseEntity.ok(household);

    }

    /**
     * Handles the household not found exception
     *
     * @param e the household not found exception
     *
     * @return the response entity with the error
     */
    @ExceptionHandler(value = HouseholdNotFoundException.class)
    public ResponseEntity handleHouseholdNotFoundException(HouseholdNotFoundException e) {
        ErrorInfo error = new ErrorInfo("Household not found.", e.getMessage(), e.getUrl());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * Handles the user not found exceptoion
     *
     * @param e the user not found exception
     *
     * @return the response entity with the error
     */
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity handleUserNotFoundException(UserNotFoundException e) {
        ErrorInfo error = new ErrorInfo("User not found.", e.getMessage(), e.getUrl());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * Handles the illegal request format exception
     *
     * @param e the illegal request format exception
     *
     * @return the response entity with the error
     */
    @ExceptionHandler(value = IllegalRequestFormatException.class)
    public ResponseEntity handleIllegalRequestFormatException(IllegalRequestFormatException e) {
        IllegalRequestFormatErrorInfo error = new IllegalRequestFormatErrorInfo("Illegal Request Format",
                e.getMessage(), e.getUrl(), e.getErrors());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
