package com.smartsync.controller;

import com.smartsync.dto.WeatherLocationDTO;
import com.smartsync.model.WeatherInformation;
import com.smartsync.model.WeatherLocation;
import com.smartsync.service.WeatherInformationService;
import com.smartsync.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jack on 3/23/17.
 */
@RestController
public class WeatherController {

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
    public ResponseEntity addWeather(@RequestBody WeatherLocationDTO weatherLocationDTO) {
        WeatherLocation weatherLocation = this.weatherService.addWeather(weatherLocationDTO);

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

        return ResponseEntity.ok(weatherLocation);
    }

    /**
     * Gets weather location for a user
     * @param userId the user id to find by
     * @return the list of weather locations for a user
     */
    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}/location", produces = "application/json")
    public ResponseEntity getWeatherLocationsForUser(@PathVariable("userId") Long userId) {
        List<WeatherLocation> weatherLocationList = this.weatherService.getWeatherLocationsForUser(userId);

        return ResponseEntity.ok(weatherLocationList);
    }

    /**
     * Gets weather information for a user
     * @param userId the user to find by
     * @return the list of weather information for a user
     */
    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}/information", produces = "application/json")
    public ResponseEntity getWeatherInformationForUser(@PathVariable("userId") Long userId) {
        List<WeatherInformation> weatherInformationList =
                this.weatherInformationService.getWeatherInformationForUser(userId);

        return ResponseEntity.ok(weatherInformationList);
    }

    /**
     * Deletes all weather information for user, useful when deleting a user
     * @param userId the user id to delete for
     * @return the list of deleted weather for a user
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "users/{userId}", produces = "application/json")
    public ResponseEntity deleteAllWeatherForUser(@PathVariable("userId") Long userId) {
        List<WeatherLocation> weatherLocationList = this.weatherService.deleteWeatherLocationsForUser(userId);

        return ResponseEntity.ok(weatherLocationList);
    }
}
