package com.smartsync.controller;

import com.smartsync.dto.ServiceDTO;
import com.smartsync.dto.ServiceTypeDTO;
import com.smartsync.dto.UpdateServiceDTO;
import com.smartsync.error.*;
import com.smartsync.model.Service;
import com.smartsync.model.ServiceType;
import com.smartsync.service.ServiceService;
import com.smartsync.service.ServiceTypeService;
import com.smartsync.validator.*;
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

    @Autowired
    private ServiceTypeService serviceTypeService;

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
     * Gets all services.
     * @return all of the services
     */
    @RequestMapping(method = RequestMethod.GET, value = "/types", produces = "application/json")
    public List<ServiceType> getAllServiceTypes() {
        return this.serviceTypeService.getAllServiceTypes();
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
            String url = "/service/" + id;
            logger.error(message);
            throw new ServiceNotFoundException(message, url);
        }

        logger.info("Successfully got service information: " + service);
        return ResponseEntity.ok(service);
    }


    /**
     * Gets serviceType by id
     *
     * @param id gets the serviceType by id
     *
     * @return the serviceType
     */
    @RequestMapping(method = RequestMethod.GET, value = "/types/{id}", produces = "application/json")
    public ResponseEntity getServiceTypeById(@PathVariable("id") Long id) {

        logger.info("Getting service type information for id: " + id);

        ServiceType serviceType = this.serviceTypeService.getServiceTypeById(id);

        if(serviceType == null) {

            String message = "Could not find service type with id " + id + ".";
            String url = "/service/types/" + id;
            logger.error(message);
            throw new ServiceTypeNotFoundException(message, url);
        }

        logger.info("Successfully got service type information: " + serviceType);
        return ResponseEntity.ok(serviceType);
    }

    /**
     * Gets service by id
     *
     * @param id gets the service type by id
     *
     * @return the service
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/types", produces = "application/json")
    public ResponseEntity getServiceTypeByServiceId(@PathVariable("id") Long id) {

        logger.info("Getting service type information for id: " + id);

        Service service = this.serviceService.getServiceById(id);

        if(service == null) {

            String message = "Could not find service with id " + id + ".";
            String url = "/service/" + id + "/types";
            logger.error(message);
            throw new ServiceNotFoundException(message, url);
        }

        ServiceType serviceType = this.serviceTypeService.getServiceTypeById(service.getServiceTypeId());

        if(serviceType == null){
            String message = "Could not find service type with id " + service.getServiceTypeId() + ".";
            String url = "/service/" + id + "/types";
            logger.error(message);
            throw new ServiceTypeNotFoundException(message, url);
        }

        logger.info("Successfully got service information: " + serviceType);
        return ResponseEntity.ok(serviceType);
    }


    /**
     * Adds a new service type to the database
     *
     * @param serviceTypeDTO the user dto to add
     * @param errors the errors list
     *
     * @return the service that was added
     */
    @RequestMapping(method = RequestMethod.POST, value = "/types", produces = "application/json")
    public ResponseEntity addServiceType(@RequestBody ServiceTypeDTO serviceTypeDTO, Errors errors) {
        logger.info("Adding new service type: " + serviceTypeDTO);

        ServiceTypeValidator serviceTypeValidator = new ServiceTypeValidator();
        serviceTypeValidator.validate(serviceTypeDTO, errors);

        ValidationError validationError = ValidationErrorBuilder.fromBindErrors(errors);


        if(errors.hasErrors()) {

            logger.error("Service type could not be created: " + validationError.getErrors());
            throw new IllegalRequestFormatException("Could not add service type.", "/service/type/", validationError);
        }

        ServiceType serviceType = new ServiceType(serviceTypeDTO);
        ServiceType savedServiceType = this.serviceTypeService.addServiceType(serviceType);
        logger.info("Successfully created new service type: " + savedServiceType);

        return ResponseEntity.ok(savedServiceType);
    }


    /**
     * Adds a new service to the database
     *
     * @param serviceDTO the user dto to add
     * @param errors the errors list
     *
     * @return the service that was added
     */
    @RequestMapping(method = RequestMethod.POST, value = "/", produces = "application/json")
    public ResponseEntity addService(@RequestBody ServiceDTO serviceDTO, Errors errors) {
        logger.info("Adding new service: " + serviceDTO);

        ServiceValidator serviceValidator = new ServiceValidator();
        serviceValidator.validate(serviceDTO, errors);

        ValidationError validationError = ValidationErrorBuilder.fromBindErrors(errors);


        if(errors.hasErrors()) {

            logger.error("Service could not be created: " + validationError.getErrors());
            throw new IllegalRequestFormatException("Could not add service.", "/users/", validationError);
        }

        logger.info("Getting service type information for id: " + serviceDTO.getServiceTypeId());

        ServiceType serviceType = this.serviceTypeService.getServiceTypeById(serviceDTO.getServiceTypeId());

        if(serviceType == null) {

            String message = "Could not find service type with id " + serviceDTO.getServiceTypeId() + ".";
            String url = "/service/types/" + serviceDTO.getServiceTypeId();
            logger.error(message);
            throw new ServiceTypeNotFoundException(message, url);
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
     * Deletes a service by id
     * @param id the service id
     * @return the service that was deleted
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
     * Handles the serviceType not found exception
     * @param e the serviceType not found exception
     * @return the response entity with the error
     */
    @ExceptionHandler(value = ServiceTypeNotFoundException.class)
    public ResponseEntity handleServiceTypeNotFoundException(ServiceTypeNotFoundException e) {
        ErrorInfo error = new ErrorInfo("ServiceType Not Found", e.getMessage(), e.getUrl());

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
