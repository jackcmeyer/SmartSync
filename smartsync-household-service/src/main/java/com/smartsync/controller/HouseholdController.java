package com.smartsync.controller;

import com.smartsync.dto.ServiceAndHouseholdDTO;
import com.smartsync.dto.UserAndHouseholdDTO;
import com.smartsync.dto.UpdateHouseholdDTO;
import com.smartsync.dto.HouseholdDTO;
import com.smartsync.error.*;
import com.smartsync.model.Household;
import com.smartsync.model.HouseholdServiceLookup;
import com.smartsync.model.HouseholdUserLookup;
import com.smartsync.service.HouseholdService;
import com.smartsync.service.HouseholdServiceLookupService;
import com.smartsync.service.HouseholdUserLookupService;
import com.smartsync.validator.*;
import communication.ServiceServiceCommunication;
import communication.UserServiceCommunication;
import model.ServicePOJO;
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

    private UserServiceCommunication userServiceCommunication = new UserServiceCommunication();
    private ServiceServiceCommunication serviceServiceCommunication = new ServiceServiceCommunication();


    @Autowired
    private HouseholdUserLookupService householdUserLookupService;

    @Autowired
    private HouseholdServiceLookupService householdServiceLookupService;

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
        householdList = this.householdService.getAllHouseholds();

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
        System.out.println(householdDTO.toString());

        validator.validate(householdDTO, errors);
        if(errors.hasErrors()) {
            ValidationError validationError = ValidationErrorBuilder.fromBindErrors(errors);
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

    /**
     * Gets the users that are a part of the household
     * @param id the household id
     * @return list of users in the household
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/users", produces = "application/json")
    public ResponseEntity getUsersInHousehold(@PathVariable("id") Long id) {

        Household household = this.householdService.getHouseHoldById(id);
        if(household == null) {
            String message = "Could not find household with id " + id + ".";
            String url = "households/" + id + "/users";

            logger.error(message);
            throw new HouseholdNotFoundException(message, url);
        }


        List<UserPOJO> users = this.householdUserLookupService.getUsersInHousehold(id);

        logger.info("Successfully found users in household with id " + id + "\n" + users);
        return ResponseEntity.ok(users);

    }

    /**
     * Gets the services associated with this household.
     * @param id the household id
     * @return list of users in the household
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/services", produces = "application/json")
    public ResponseEntity getHouseholdServices(@PathVariable("id") Long id) {

        Household household = this.householdService.getHouseHoldById(id);
        if(household == null) {
            String message = "Could not find household with id " + id + ".";
            String url = "households/" + id + "/services";

            logger.error(message);
            throw new HouseholdNotFoundException(message, url);
        }


        List<ServicePOJO> services = this.householdServiceLookupService.getHouseholdServices(id);

        logger.info("Successfully found services in household with id " + id + "\n" + services);
        return ResponseEntity.ok(services);

    }

    /**
     * Adds a service to a household
     *
     * @param serviceAndHouseholdDTO the add service to household dto
     *
     * @return success
     */
    @RequestMapping(method = RequestMethod.POST, value = "/services", produces = "application/json")
    public ResponseEntity addServiceToHousehold(@RequestBody ServiceAndHouseholdDTO serviceAndHouseholdDTO, Errors errors) {

        ServiceAndHouseholdValidator serviceAndHouseholdValidator = new ServiceAndHouseholdValidator();
        serviceAndHouseholdValidator.validate(serviceAndHouseholdDTO, errors);

        // check if there was any errors with the request body
        if(errors.hasErrors()) {
            ValidationError validationError = ValidationErrorBuilder.fromBindErrors(errors);
            String message = "Could not add service to household.";
            String url = "/households/service";

            logger.error("Could not create new household service: " + errors);
            throw new IllegalRequestFormatException(message, url, validationError);
        }

        Long serviceId = serviceAndHouseholdDTO.getServiceId();
        Long id = serviceAndHouseholdDTO.getHouseholdId();

        // check if the user exists
        ServicePOJO service = serviceServiceCommunication.getService(serviceId);
        if(service == null) {
            String message = "Could not find service with id " + serviceId + ".";
            String url = "/households/service/";
            logger.error(message);

            throw new ServiceNotFoundException(message, url);
        }

        // check if the household exists
        Household household = this.householdService.getHouseHoldById(id);
        if(household == null) {
            String message = "Could not find household with id " + id + ".";
            String url = "/households/service";

            logger.error(message);
            throw new HouseholdNotFoundException(message, url);
        }

        // error adding the user to the household
        HouseholdServiceLookup savedHouseholdServiceLookUp = this.householdServiceLookupService.addServiceToHouseHold(serviceId, id);
        if(savedHouseholdServiceLookUp == null) {
            String message = "Could not add service with id " + serviceId + " to household with id " + id;
            String url = "/households/service";

            logger.error(message);
            throw new HouseholdNotFoundException(message, url);
        }

        logger.info("Successfully added service with id " + serviceId + " to the household with id " + id);
        return ResponseEntity.ok().body(savedHouseholdServiceLookUp);

    }

    /**
     * Removes a service from a household
     * @param id the household id
     * @param serviceId the userId
     * @return the response entity with the UserHouseHoldLookUp that was removed
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "{id}/services/{serviceId}", produces = "application/json")
    public ResponseEntity removeServiceFromHousehold(@PathVariable("id") Long id, @PathVariable("serviceId") Long serviceId) {

        ServicePOJO servicePOJO = this.serviceServiceCommunication.getService(serviceId);
        // if the user does not exist
        if(servicePOJO == null) {
            String message = "Could not remove service with id: " + serviceId + " because the service" +
                    "does not exist";
            String url = "/households/service";

            logger.error(message);
            throw new ServiceNotFoundException(message, url);
        }

        // check if the household exists
        Household household = this.householdService.getHouseHoldById(id);
        if(household == null) {
            String message = "Could not remove service with id: " + id + " because the " +
                    "household with id: " + id + " does not exist";
            String url = "/households/service";

            logger.error(message);
            throw new HouseholdNotFoundException(message, url);
        }

        HouseholdServiceLookup householdServiceLookup =
                this.householdServiceLookupService.removeServiceFromHousehold(serviceId,
                        id);

        // check if the user was actually in the household
        if(householdServiceLookup == null) {
            String message = "Error removing service with id: " + serviceId + " from household with" +
                    "id "  + id;
            String url = "/households/service";

            logger.error(message);
            throw new HouseholdNotFoundException(message, url);
        }

        return ResponseEntity.ok(householdServiceLookup);
    }

    /**
     * Adds a user to a household
     *
     * @param userAndHouseholdDTO the add user to household dto
     *
     * @return success
     */
    @RequestMapping(method = RequestMethod.POST, value = "/users", produces = "application/json")
    public ResponseEntity addUserToHousehold(@RequestBody UserAndHouseholdDTO userAndHouseholdDTO, Errors errors) {

        UserAndHouseholdValidator userAndHouseholdValidator = new UserAndHouseholdValidator();
        userAndHouseholdValidator.validate(userAndHouseholdDTO, errors);

        // check if there was any errors with the request body
        if(errors.hasErrors()) {
            ValidationError validationError = ValidationErrorBuilder.fromBindErrors(errors);
            String message = "Could not add user to household.";
            String url = "/households/users";

            logger.error("Could not create new household: " + errors);
            throw new IllegalRequestFormatException(message, url, validationError);
        }

        Long userId = userAndHouseholdDTO.getUserId();
        Long id = userAndHouseholdDTO.getHouseholdId();

        // check if the user exists
        UserPOJO user = userServiceCommunication.getUser(userId);
        if(user == null) {
            String message = "Could not find user with id " + userId + ".";
            String url = "/households/users/";

            logger.error(message);

            throw new UserNotFoundException(message, url);
        }

        // check if the household exists
        Household household = this.householdService.getHouseHoldById(id);
        if(household == null) {
            String message = "Could not find household with id " + id + ".";
            String url = "/households/users";

            logger.error(message);
            throw new HouseholdNotFoundException(message, url);
        }

        // check if the user is already in a household
        HouseholdUserLookup householdUserLookup = this.householdUserLookupService.getHouseholdForUser(userId);
        if(householdUserLookup != null) {
            String message = "User with id " + userId + " is already in a household with id " + id;
            String url = "households/users";

            logger.error(message);
            throw new UserAlreadyInHouseholdException(message, url);
        }

        // error adding the user to the household
        HouseholdUserLookup savedHouseholdUserLookUp = this.householdUserLookupService.addUserToHouseHold(userId, id);
        if(savedHouseholdUserLookUp == null) {
            String message = "Could not add user with id " + userId + " to household with id " + id;
            String url = "/households/users";

            logger.error(message);
            throw new HouseholdNotFoundException(message, url);
        }

        logger.info("Successfully added user  with id " + userId + " to the household with id " + id);
        return ResponseEntity.ok().body(savedHouseholdUserLookUp);

    }

    /**
     * Removes a user from a household
     * @param id the household id
     * @param userId the userId
     * @return the response entity with the UserHouseHoldLookUp that was removed
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "{id}/users/{userId}", produces = "application/json")
    public ResponseEntity removeUserFromHousehold(@PathVariable("id") Long id, @PathVariable("userId") Long userId) {

        UserPOJO userPOJO = this.userServiceCommunication.getUser(userId);
        // if the user does not exist
        if(userPOJO == null) {
            String message = "Could not remove user with id: " + userId + " because the user" +
                    "does not exist";
            String url = "/households/users";

            logger.error(message);
            throw new UserNotFoundException(message, url);
        }

        // check if the household exists
        Household household = this.householdService.getHouseHoldById(id);
        if(household == null) {
            String message = "Could not remove user with id: " + id + " because the " +
                    "household with id: " + id + " does not exist";
            String url = "/households/users";

            logger.error(message);
            throw new HouseholdNotFoundException(message, url);
        }

        HouseholdUserLookup householdUserLookup =
                this.householdUserLookupService.removeUserFromHousehold(userId,
                        id);

        // check if the user was actually in the household
        if(householdUserLookup == null) {
            String message = "Error removing user with id: " + userId + " from household with" +
                    "id "  + id;
            String url = "/households/users";

            logger.error(message);
            throw new HouseholdNotFoundException(message, url);
        }

        return ResponseEntity.ok(householdUserLookup);
    }

    /**
     * Gets the household which the user is a part of
     * @param userId the user to find the household
     * @return the household the user is a part of
     */
    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}", produces = "application/json")
    public ResponseEntity getHouseholdForUser(@PathVariable("userId") Long userId) {

        UserPOJO user = userServiceCommunication.getUser(userId);
        if(user == null) {
            String message = "Could not find user with id " + userId + ".";
            String url = "/households/users/" + userId;

            logger.error(message);
            throw new UserNotFoundException(message, url);
        }


        HouseholdUserLookup householdUserLookup = this.householdUserLookupService.getHouseholdForUser(userId);
        Household household = this.householdService.getHouseHoldById(householdUserLookup.getHouseholdId());
        if(household == null || householdUserLookup == null) {
            String message = "Could not find household for user with id " + userId +  ".";
            String url = "/households/users/" + userId;

            logger.error(message);
            throw new HouseholdNotFoundException(message, url);
        }



        logger.info("Successfully found household for user with id " + userId + ": " + household);
        return ResponseEntity.ok(household);

    }

    /**
     * Updates the househole information
     * @param updateHouseholdDTO the user information to update
     * @param errors an error container
     * @return the udpated user
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/", produces = "application/json")
    public ResponseEntity updateHousehold(@RequestBody UpdateHouseholdDTO updateHouseholdDTO, Errors errors) {

        UpdateHouseholdValidator houseValidator = new UpdateHouseholdValidator();
        houseValidator.validate(updateHouseholdDTO, errors);

        ValidationError validationError = ValidationErrorBuilder.fromBindErrors(errors);

        if(errors.hasErrors()) {
            throw new IllegalRequestFormatException("Could not update household.", "/household", validationError);
        }

        Household updatedHousehold= this.householdService.updateHousehold(updateHouseholdDTO);

        return ResponseEntity.ok(updatedHousehold);
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

    @ExceptionHandler(value = UserAlreadyInHouseholdException.class)
    public ResponseEntity handleUserAlreadyInHouseholdException(UserAlreadyInHouseholdException e) {
        ErrorInfo error = new ErrorInfo("User Already In Household", e.getMessage(), e.getUrl());

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
