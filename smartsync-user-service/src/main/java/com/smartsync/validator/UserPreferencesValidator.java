package com.smartsync.validator;

import com.smartsync.dto.UserPreferencesDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The user preferences validator
 */
public class UserPreferencesValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return UserPreferencesDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserPreferencesDTO userPreferencesDTO = (UserPreferencesDTO) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userId", "field.required",
                "User Id must not be empty.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required",
                "Name must not be empty");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "primaryColor", "field.required",
                "Primary Color must not be empty");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "secondaryColor", "field.required",
                "Secondary Color must not be empty");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accentColor", "field.required",
                "Accent Color must not be empty");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "neutralLightColor", "field.required",
                "Neutral Light Color must not be empty");


        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "neutralDarkColor", "field.required",
                "Neutral Dark Color must not be empty");
    }
}
