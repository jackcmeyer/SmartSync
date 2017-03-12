package com.smartsync.controller;

import com.smartsync.dto.HouseholdDTO;
import com.smartsync.error.ErrorInfo;
import com.smartsync.error.HouseholdNotFoundException;
import com.smartsync.error.IllegalRequestFormatErrorInfo;
import com.smartsync.error.IllegalRequestFormatException;
import com.smartsync.model.Household;
import com.smartsync.service.HouseholdService;
import com.smartsync.service.HouseholdUserLookupService;
import com.smartsync.validator.HouseholdValidator;
import com.smartsync.validator.ValidationError;
import com.smartsync.validator.ValidationErrorBuilder;
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

        Household h = this.householdService.addHousehold(new Household(householdDTO));

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
