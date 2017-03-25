package com.smartsync.service;

import com.smartsync.dto.WeatherLocationDTO;
import com.smartsync.model.WeatherInformation;
import com.smartsync.model.WeatherInformationRepository;
import com.smartsync.model.WeatherLocation;
import com.smartsync.model.WeatherLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The weather service
 */
@Component
public class WeatherService {

    @Autowired
    private WeatherInformationRepository weatherInformationRepository;

    @Autowired
    private WeatherLocationRepository weatherLocationRepository;

    public WeatherService() {

    }

    /**
     * Gets all weather locations
     * @return the list of weather locations
     */
    public List<WeatherLocation> getAllWeather() {
        return this.weatherLocationRepository.findAll();
    }

    /**
     * Adds weather to the database
     * @param weatherLocationDTO the weather dto
     * @return
     */
    public WeatherLocation addWeather(WeatherLocationDTO weatherLocationDTO) {

        WeatherLocation weatherLocation = new WeatherLocation(weatherLocationDTO);
        this.weatherLocationRepository.save(weatherLocation);


        WeatherUndergroundService weatherUndergroundService = new WeatherUndergroundService();
        weatherUndergroundService.getWeatherForLocation("Ames", "IA");


        WeatherInformation weatherInformation =
                new WeatherInformation("cloudy", 88.0, 89.0, 10.0, "NNW");
        weatherInformation.setWeatherLocation(weatherLocation);
        this.weatherInformationRepository.save(weatherInformation);

        return weatherLocation;

    }

    /**
     * Gets weather location by id
     * @param id the id to find by
     * @return the weather location
     */
    public WeatherLocation getWeatherLocationById(Long id) {
        return this.weatherLocationRepository.findById(id);
    }

    /**
     * Deletes weather location by id
     * @param id the id to delete
     * @return the deleted weather location
     */
    public WeatherLocation deleteWeatherLocationById(Long id) {
        WeatherLocation weatherLocation = this.weatherLocationRepository.findById(id);

        this.weatherLocationRepository.delete(weatherLocation);

        return weatherLocation;
    }

    /**
     * Gets all weather locations for a user
     * @param userId the user id to find by
     * @return the list of weather locations for a user
     */
    public List<WeatherLocation> getWeatherLocationsForUser(Long userId) {
        return this.weatherLocationRepository.findByUserId(userId);
    }

    /**
     * Deletes weather location and information for user
     * @param userId the user id to delete for
     * @return the list of deleted weather locations
     */
    public List<WeatherLocation> deleteWeatherLocationsForUser(Long userId) {

        List<WeatherLocation> weatherLocationList = this.weatherLocationRepository.findByUserId(userId);

        List<WeatherInformation> weatherInformationList = new ArrayList<>();
        for(WeatherLocation weatherLocation : weatherLocationList) {
            this.weatherInformationRepository.findByWeatherLocationId(weatherLocation.getId());
        }

        this.weatherLocationRepository.delete(weatherLocationList);
        this.weatherInformationRepository.delete(weatherInformationList);

        return weatherLocationList;

    }


}
