package com.smartsync.dto;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 */
public class WeatherLocationDTO {

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

    public WeatherLocationDTO(Long userId, String city, String state) {
        this.userId = userId;
        this.city = city;
        this.state = state;
    }

    public WeatherLocationDTO() {
        // default constructor
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
}
