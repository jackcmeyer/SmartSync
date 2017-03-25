package com.smartsync.validator;

import com.smartsync.dto.WeatherLocationDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * Validates the weather dto
 */
public class WeatherValidator implements Validator{
    @Override
    public boolean supports(Class<?> aClass) {
        return WeatherLocationDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {

        WeatherLocationDTO weatherLocationDTO = (WeatherLocationDTO) object;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userId", "field.required",
                "User Id must not be empty");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "field.required",
                "City must not be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state", "field.required",
                "State must not be empty");

        if(!errors.hasErrors()) {
            if(weatherLocationDTO.getState().length() != 2) {
                errors.rejectValue("state", "filed.invalid",
                        "State must be length 2. Did you forget to use state code?");
            }

        }


    }
}
