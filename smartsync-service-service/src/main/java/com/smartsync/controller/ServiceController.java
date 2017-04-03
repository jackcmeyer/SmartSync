package com.smartsync.controller;

import com.smartsync.dto.ServiceDTO;
import com.smartsync.dto.UpdateServiceDTO;
import com.smartsync.error.*;
import com.smartsync.model.Service;
import com.smartsync.service.ServiceService;
import com.smartsync.validator.UpdateServiceValidator;
import com.smartsync.validator.ServiceValidator;
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
 * The Service Controller
 */
@RestController
public class ServiceController {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private ServiceService serviceService;

    public ServiceController() {

    }

    /**
     * Gets all services.
     * @return all of the services
     */
    @RequestMapping(method = RequestMethod.GET, value = "/", produces = "application/json")
    public List<Service> getAllServices() {
        return this.serviceService.getAllServices();
    }

    /**
     * Gets service by id
     *
     * @param id gets the service by id
     *
     * @return the service
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = "application/json")
    public ResponseEntity getServiceById(@PathVariable("id") Long id) {

        logger.info("Getting service information for id: " + id);

        Service service = this.serviceService.getServiceById(id);

        if(service == null) {

            String message = "Could not find service with id " + id + ".";
            String url = "/users/" + id;

            logger.error(message);
            throw new ServiceNotFoundException(message, url);
        }

        logger.info("Successfully got service information: " + service);
        return ResponseEntity.ok(service);
    }


    /**
     * Adds a new user to the database
     *
     * @param serviceDTO the user dto to add
     * @param errors the errors list
     *
     * @return the user that was addded
     */
    @RequestMapping(method = RequestMethod.POST, value = "/", produces = "application/json")
    public ResponseEntity addService(@RequestBody ServiceDTO serviceDTO, Errors errors) {
        //TODO fix this to add a new service to a given householdId
        logger.info("Adding new service: " + serviceDTO);

        ServiceValidator serviceValidator = new ServiceValidator();
        serviceValidator.validate(serviceDTO, errors);

        ValidationError validationError = ValidationErrorBuilder.fromBindErrors(errors);


        if(errors.hasErrors()) {

            logger.error("Service could not be created: " + validationError.getErrors());
            throw new IllegalRequestFormatException("Could not add service.", "/users/", validationError);
        }

        Service service = new Service(serviceDTO);
        Service savedService = this.serviceService.addService(service);
        logger.info("Successfully created new service: " + savedService);

        return ResponseEntity.ok(savedService);
    }

    /**
     * Updates the user information
     * @param updateServiceDTO the user information to update
     * @param errors an error container
     * @return the udpated user
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/", produces = "application/json")
    public ResponseEntity updateService(@RequestBody UpdateServiceDTO updateServiceDTO, Errors errors) {

        UpdateServiceValidator userValidator = new UpdateServiceValidator();
        userValidator.validate(updateServiceDTO, errors);

        ValidationError validationError = ValidationErrorBuilder.fromBindErrors(errors);

        if(errors.hasErrors()) {
            throw new IllegalRequestFormatException("Could not update service.", "/services", validationError);
        }

        Service updatedService = this.serviceService.updateService(updateServiceDTO);

        return ResponseEntity.ok(updatedService);
    }

    /**
     * Deletes a user by id
     * @param id the user id
     * @return the user that was deleted
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", produces = "application/json")
    public ResponseEntity deleteService(@PathVariable("id") Long id) {

        Service service = this.serviceService.deleteService(id);

        if(service == null) {
            String message = "Could not delete service with id: " + id;
            String url = "/services/" + id;

            logger.error("Could not find service with id: " + id + " to delete");
            throw new ServiceNotFoundException(message, url);
        }


        logger.info("Successfully deleted service: " + service);
        return ResponseEntity.ok(service);
    }


    /**
     * Handles the user not found exception
     * @param e the user not found exception
     * @return the response entity with the error
     */
    @ExceptionHandler(value = ServiceNotFoundException.class)
    public ResponseEntity handleServiceNotFoundException(ServiceNotFoundException e) {
        ErrorInfo error = new ErrorInfo("Service Not Found", e.getMessage(), e.getUrl());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }


    /**
     * Handles the household not found exception
     * @param e the household not found exception
     * @return the response entity with the error
     */
    @ExceptionHandler(value = ServiceNotFoundException.class)
    public ResponseEntity handleHouseholdNotFoundException(ServiceNotFoundException e) {
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
