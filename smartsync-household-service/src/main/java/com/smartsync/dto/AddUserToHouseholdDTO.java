package com.smartsync.dto;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The add user dto, contains a user id and household id
 */
public class AddUserToHouseholdDTO {

    /**
     * The household id that we are adding a user to
     */
    private Long householdId;

    /**
     * The user id we are adding to a household
     */
    private Long userId;

    public AddUserToHouseholdDTO(Long householdId, Long userId) {
        this.householdId = householdId;
        this.userId = userId;
    }

    public AddUserToHouseholdDTO() {
    }

    public Long getHouseholdId() {
        return householdId;
    }

    public void setHouseholdId(Long householdId) {
        this.householdId = householdId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
