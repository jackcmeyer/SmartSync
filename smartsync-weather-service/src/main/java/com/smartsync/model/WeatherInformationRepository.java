package com.smartsync.model;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The Weather Information Repository
 */
public interface WeatherInformationRepository extends JpaRepository<WeatherInformation, Long> {

    WeatherInformation findByWeatherLocationId(Long weatherLocationId);
}
