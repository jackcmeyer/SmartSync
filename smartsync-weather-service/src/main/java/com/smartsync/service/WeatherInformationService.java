package com.smartsync.service;

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
 * The weather information service
 */
@Component
public class WeatherInformationService {

    @Autowired
    private WeatherInformationRepository weatherInformationRepository;

    @Autowired
    private WeatherLocationRepository weatherLocationRepository;

    /**
     * Gets all weather information
     * @return the list of all weather information
     */
    public List<WeatherInformation> getAllWeatherInformation() {
        return this.weatherInformationRepository.findAll();
    }

    /**
     * Gets weather information by location id
     * @param weatherLocationId the location id
     * @return the weather information
     */
    public WeatherInformation getWeatherInformationById(Long weatherLocationId) {
        return this.weatherInformationRepository.findByWeatherLocationId(weatherLocationId);
    }

    /**
     * Gets weather information for a user
     * @param userId the user to get weather information for
     * @return the list of weather information
     */
    public List<WeatherInformation> getWeatherInformationForUser(Long userId) {
        List<WeatherLocation> weatherLocationList = this.weatherLocationRepository.findByUserId(userId);

        List<WeatherInformation> weatherInformationList = new ArrayList<>();
        for(WeatherLocation weatherLocation : weatherLocationList) {
            WeatherInformation weatherInformation =
                    this.weatherInformationRepository.findByWeatherLocationId(weatherLocation.getId());

            weatherInformationList.add(weatherInformation);
        }

        return weatherInformationList;
    }

    /**
     * Deletes weather information by id
     * @param weatherLocationId the weather location id
     * @return the deleted weather information
     */
    public WeatherInformation deleteWeatherInformationById(Long weatherLocationId) {
        WeatherInformation weatherInformation =
                this.weatherInformationRepository.findByWeatherLocationId(weatherLocationId);

        this.weatherInformationRepository.delete(weatherInformation);

        return weatherInformation;
    }
}
