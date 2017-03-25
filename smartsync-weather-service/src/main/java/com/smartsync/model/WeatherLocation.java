package com.smartsync.model;

import com.smartsync.dto.WeatherLocationDTO;

import javax.persistence.*;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * Model which stores the weather locations
 */
@Entity
public class WeatherLocation {

    /**
     * The id
     */
    @GeneratedValue @Id
    private Long id;

    /**
     * The user id
     */
    private Long userId;

    /**
     * The city
     */
    private String city;

    /**
     * The state
     */
    private String state;

    public WeatherLocation(Long userId, String city, String state) {
        this.userId = userId;
        this.city = city;
        this.state = state;
    }

    public WeatherLocation(WeatherLocationDTO weatherLocationDTO) {
        this.userId = weatherLocationDTO.getUserId();
        this.city = weatherLocationDTO.getCity();
        this.state = weatherLocationDTO.getState();
    }

    public WeatherLocation() {
        // default constructor
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "WeatherLocation{" +
                "id=" + id +
                ", userId=" + userId +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
