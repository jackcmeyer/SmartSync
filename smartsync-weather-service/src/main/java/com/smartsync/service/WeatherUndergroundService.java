package com.smartsync.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.smartsync.dto.WeatherUndergroundDTO;
import com.smartsync.util.HttpUtil;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The weather underground service
 */
@Component
public class WeatherUndergroundService {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    private static final String WEATHER_UNDERGROUND_API_TOKEN = "API KEY HERE";

    private static final String WEATHER_UNDERGROUND_BASE_URL = "http://api.wunderground.com/api/"
            + WEATHER_UNDERGROUND_API_TOKEN + "/conditions/q/";

    public WeatherUndergroundService() {

    }

    /**
     * Gets weather from the weather underground api for a given city and state
     * @param city the city
     * @param state the state
     * @return the weather underground data
     */
    public WeatherUndergroundDTO getWeatherForLocation(String city, String state) {


        city = city.replaceAll(" ", "_");
        String requestUrl = WEATHER_UNDERGROUND_BASE_URL + state + "/" + city + ".json";

        logger.info("Sending request to " + requestUrl);


        String response = HttpUtil.executeGetRequest(requestUrl);
        WeatherUndergroundDTO weatherUndergroundDTO = jsonToWeatherUndergroundDTO(response);

        logger.info("Response " + response);

        return weatherUndergroundDTO;
    }

    /**
     * Converts a weather underground api response to a weather underground dto
     * @param json the json string
     * @return the weather underground dto
     */
    private WeatherUndergroundDTO jsonToWeatherUndergroundDTO(String json) {
        Gson gson = new Gson();

        JsonObject mainJson = gson.fromJson(json, JsonObject.class);
        JsonObject currentObservationJson = mainJson.get("current_observation").getAsJsonObject();

        String weather = currentObservationJson.get("weather").getAsString();
        double temperature = currentObservationJson.get("temp_f").getAsDouble();
        double feelsLikeTemperature = currentObservationJson.get("feelslike_f").getAsDouble();
        double windSpeed = currentObservationJson.get("wind_mph").getAsDouble();
        String windDirection = currentObservationJson.get("wind_dir").getAsString();

        WeatherUndergroundDTO weatherUndergroundDTO = new WeatherUndergroundDTO(weather, temperature,
                feelsLikeTemperature, windSpeed, windDirection);

        return weatherUndergroundDTO;
    }

}
