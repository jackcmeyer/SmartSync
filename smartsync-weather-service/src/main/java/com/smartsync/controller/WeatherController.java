package com.smartsync.controller;

import com.smartsync.dto.WeatherLocationDTO;
import com.smartsync.error.*;
import com.smartsync.model.WeatherInformation;
import com.smartsync.model.WeatherLocation;
import com.smartsync.service.WeatherInformationService;
import com.smartsync.service.WeatherService;
import com.smartsync.validator.ValidationError;
import com.smartsync.validator.ValidationErrorBuilder;
import com.smartsync.validator.WeatherValidator;
import communication.UserServiceCommunication;
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
 * The weather controller
 */
@RestController
public class WeatherController {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private WeatherInformationService weatherInformationService;

    /**
     * Gets all weather location in the database
     * @return the list of all weather location
     */
    @RequestMapping(method = RequestMethod.GET, value = "/location", produces = "application/json")
    public ResponseEntity getAllWeatherLocations() {
        List<WeatherLocation> weatherLocationList = this.weatherService.getAllWeather();

        return ResponseEntity.ok(this.weatherService.getAllWeather());
    }

    /**
     * Gets all weather information in the database
     * @return the list of all weather information
     */
    @RequestMapping(method = RequestMethod.GET, value = "/information", produces = "application/json")
    public ResponseEntity getAllWeatherInformation() {
        List<WeatherInformation> weatherInformationList = this.weatherInformationService.getAllWeatherInformation();

        return ResponseEntity.ok(weatherInformationList);
    }

    /**
     * Adds weather information
     * @param weatherLocationDTO the weather dto
     * @returnt the saved weather information
     */
    @RequestMapping(method = RequestMethod.POST, value = "/", produces = "application/json")
    public ResponseEntity addWeather(@RequestBody WeatherLocationDTO weatherLocationDTO, Errors errors) {


        WeatherValidator validator = new WeatherValidator();
        validator.validate(weatherLocationDTO, errors);

        // validate the weather location dto
        if(errors.hasErrors()) {
            String message = "Could not add new weather location " + weatherLocationDTO;
            String url = "/weather/";

            ValidationError validationError = ValidationErrorBuilder.fromBindErrors(errors);

            logger.error(message + "\n" + "Errors: " + validationError);
            throw new IllegalRequestFormatException(message, url, validationError);
        }

        // make sure the use exists
        UserServiceCommunication userServiceCommunication = new UserServiceCommunication();
        UserPOJO user = userServiceCommunication.getUser(weatherLocationDTO.getUserId());
        if(user == null) {
            String message = "Could not find user with id " + weatherLocationDTO.getUserId();
            String url = "weather/";

            logger.error(message);
            throw new UserNotFoundException(message, url);
        }


        WeatherLocation weatherLocation = this.weatherService.addWeather(weatherLocationDTO);
        logger.info("Successfully added new weather location " + weatherLocation);
        return ResponseEntity.ok(weatherLocation);
    }

    /**
     * Gets weather location by id
     * @param id the id to find by
     * @return the weather location
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/location", produces = "application/json")
    public ResponseEntity getWeatherLocationById(@PathVariable("id") Long id) {
        WeatherLocation weatherLocation = this.weatherService.getWeatherLocationById(id);

        if(weatherLocation == null) {
            String message = "Could not find weather location with id " + id;
            String url = "/" + id;

            logger.error(message);
            throw new WeatherNotFoundException(message, url);
        }

        return ResponseEntity.ok(weatherLocation);
    }

    /**
     * Gets weather information by id
     * @param id the id to find by
     * @return the weather information
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/information", produces = "application/json")
    public ResponseEntity getWeatherInformationByid(@PathVariable("id") Long id) {
        WeatherInformation weatherInformation = this.weatherInformationService.getWeatherInformationById(id);

        if(weatherInformation == null) {
            String message = "Could not find weather information with id " + id;
            String url = "/" + id;

            logger.error(message);
            throw new WeatherNotFoundException(message, url);
        }

        return ResponseEntity.ok(weatherInformation);
    }

    /**
     * Deletes weather location and information by id
     * @param id the id to delete by
     * @return the deleted weather location
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", produces = "application/json")
    public ResponseEntity deleteWeatherById(@PathVariable("id") Long id) {
        WeatherLocation weatherLocation = this.weatherService.deleteWeatherLocationById(id);
        WeatherInformation weatherInformation = this.weatherInformationService.deleteWeatherInformationById(id);

        if(weatherLocation == null || weatherInformation == null) {
            String message = "Could not delete weather with id " + id + " because it could not be found";
            String url = "/" + id;

            logger.error(message);
            throw new WeatherNotFoundException(message, url);
        }

        return ResponseEntity.ok(weatherLocation);
    }

    /**
     * Gets weather location for a user
     * @param userId the user id to find by
     * @return the list of weather locations for a user
     */
    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}/location", produces = "application/json")
    public ResponseEntity getWeatherLocationsForUser(@PathVariable("userId") Long userId) {

        UserServiceCommunication userServiceCommunication = new UserServiceCommunication();
        UserPOJO user = userServiceCommunication.getUser(userId);

        if(user == null) {
            String message = "Could not find user with id " + userId;
            String url = "weather/";

            logger.error(message);
            throw new UserNotFoundException(message, url);
        }


        List<WeatherLocation> weatherLocationList = this.weatherService.getWeatherLocationsForUser(userId);

        logger.info("Successfully got weather locations for user: " + weatherLocationList);
        return ResponseEntity.ok(weatherLocationList);
    }

    /**
     * Gets weather information for a user
     * @param userId the user to find by
     * @return the list of weather information for a user
     */
    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}/information", produces = "application/json")
    public ResponseEntity getWeatherInformationForUser(@PathVariable("userId") Long userId) {

        UserServiceCommunication userServiceCommunication = new UserServiceCommunication();
        UserPOJO user = userServiceCommunication.getUser(userId);

        if(user == null) {
            String message = "Could not find user with id " + userId;
            String url = "weather/";

            logger.error(message);
            throw new UserNotFoundException(message, url);
        }

        List<WeatherInformation> weatherInformationList =
                this.weatherInformationService.getWeatherInformationForUser(userId);

        logger.info("Successfully got weather information for user: " + weatherInformationList);
        return ResponseEntity.ok(weatherInformationList);
    }

    /**
     * Deletes all weather information for user, useful when deleting a user
     * @param userId the user id to delete for
     * @return the list of deleted weather for a user
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "users/{userId}", produces = "application/json")
    public ResponseEntity deleteAllWeatherForUser(@PathVariable("userId") Long userId) {

        UserServiceCommunication userServiceCommunication = new UserServiceCommunication();
        UserPOJO user = userServiceCommunication.getUser(userId);

        if(user == null) {
            String message = "Could not find user with id " + userId;
            String url = "weather/";

            logger.error(message);
            throw new UserNotFoundException(message, url);
        }

        List<WeatherLocation> weatherLocationList = this.weatherService.deleteWeatherLocationsForUser(userId);

        logger.info("Successfully deleted weather locations for user: " + weatherLocationList);
        return ResponseEntity.ok(weatherLocationList);
    }

    /**
     * Handles the weather not found exception
     * @param e the weather not found exceptino
     * @return the response entity with the error
     */
    @ExceptionHandler(value = WeatherNotFoundException.class)
    public ResponseEntity handleWeatherNotFoundException(WeatherNotFoundException e) {
        ErrorInfo error = new ErrorInfo("Weather Not Found", e.getMessage(), e.getUrl());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
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
