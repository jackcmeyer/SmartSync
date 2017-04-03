package com.smartsync.validator;

import com.smartsync.dto.UserDTO;
import org.apache.commons.validator.EmailValidator;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Jack Meyer
 *
 * The User Validator
 */
public class UserValidator implements Validator {

    public boolean supports(Class clazz) {
        return UserDTO.class.equals(clazz);
    }

    /**
     * Validates the input
     * @param object the user to validate
     * @param errors the errors
     */
    public void validate(Object object, Errors errors) {

        UserDTO user = (UserDTO) object;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "googleId", "field.required",
                "googleId must not be empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullName", "field.required",
                "User Full Name must not be empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "givenName", "field.required",
                "User Given Name must not be empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "familyName", "field.required",
                "User Family Name must not be empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "imageURL", "field.required",
                "User Image URL must not be empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "field.required",
                "User Email must not be empty");
        ValidationUtils.rejectIfEmpty(errors, "role", "field.required",
                "User Role must not be empty.");

        // doing specific input validaiton, so we need to make sure all of the fields are there
        if(!errors.hasErrors()) {
            if(user.getRole() < 0 || user.getRole() > 1) {
                errors.rejectValue("role", "field.invalid", "User Role must be 0 or 1");
            }

            if(!EmailValidator.getInstance().isValid(user.getEmail())) {
                errors.rejectValue("email", "field.invalid", "User Email must be a valid email address.");
            }

            if(!UrlValidator.getInstance().isValid(user.getImageURL())) {
                errors.rejectValue("imageURL", "field.invalid", "User Image URl must be a valid web address.");
            }
        }



    }


}
