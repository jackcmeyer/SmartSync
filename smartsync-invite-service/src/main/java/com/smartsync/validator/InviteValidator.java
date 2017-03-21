package com.smartsync.validator;


import com.smartsync.dto.InviteDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The Invite Validator
 */
public class InviteValidator implements Validator {
    @Override
    public boolean supports(Class clazz) {
        return InviteDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object object, Errors errors) {
        InviteDTO inviteDTO = (InviteDTO) object;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userId", "field.required",
                "User ID must not be empty.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "householdId", "field.required",
                "Household ID must not be empty.");

    }
}
