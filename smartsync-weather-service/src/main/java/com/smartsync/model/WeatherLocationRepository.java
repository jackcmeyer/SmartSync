package com.smartsync.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The Weather location repository
 */
public interface WeatherLocationRepository extends JpaRepository<WeatherLocation, Long> {

    /**
     * Finds a weather location id
     * @param id the weather location id
     * @return the weather location
     */
    WeatherLocation findById(Long id);

    /**
     * Finds all weather locations for a user
     * @param userId the uesr id to find by
     * @return the list of weather locations for a user
     */
    List<WeatherLocation> findByUserId(Long userId);
}
