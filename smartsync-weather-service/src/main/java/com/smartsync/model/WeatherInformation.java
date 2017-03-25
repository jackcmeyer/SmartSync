package com.smartsync.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import java.util.Date;

/**
 * @author Jack Meyer
 *
 * Stores the weather information for a weather location
 */
@Entity
public class WeatherInformation {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    private WeatherLocation weatherLocation;

    /**
     * A description of the weather (Ex. cloudy)
     */
    private String weather;

    /**
     * The temperature in fahrenheit
     */
    private double temperature;

    /**
     * The feels like temperature in fahrenheit
     */
    private double feelsLikeTemperature;

    /**
     * The wind speed in mph
     */
    private double windSpeed;

    /**
     * The direction of the wind
     */
    private String windDirection;

    /**
     * The last updated time
     */
    private Date lastUpdated;

    public WeatherInformation(String weather, double temperature, double feelsLikeTemperature, double windSpeed,
                              String windDirection) {
        this.id = id;
        this.weather = weather;
        this.temperature = temperature;
        this.feelsLikeTemperature = feelsLikeTemperature;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.lastUpdated = new Date();
    }

    public WeatherInformation() {
        // default constructor
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WeatherLocation getWeatherLocation() {
        return weatherLocation;
    }

    public void setWeatherLocation(WeatherLocation weatherLocation) {
        this.weatherLocation = weatherLocation;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getFeelsLikeTemperature() {
        return feelsLikeTemperature;
    }

    public void setFeelsLikeTemperature(double feelsLikeTemperature) {
        this.feelsLikeTemperature = feelsLikeTemperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return "WeatherInformation{" +
                "id=" + id +
                ", weatherLocation=" + weatherLocation +
                ", weather='" + weather + '\'' +
                ", temperature=" + temperature +
                ", feelsLikeTemperature=" + feelsLikeTemperature +
                ", windSpeed=" + windSpeed +
                ", windDirection='" + windDirection + '\'' +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
