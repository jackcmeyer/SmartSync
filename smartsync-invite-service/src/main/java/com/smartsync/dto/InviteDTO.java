package com.smartsync.dto;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The Invite Data Transfer Object
 */
public class InviteDTO {

    /**
     * The user id
     */
    private Long userId;

    /**
     * The household id
     */
    private Long householdId;

    public InviteDTO(Long userId, Long householdId) {
        this.userId = userId;
        this.householdId = householdId;
    }

    public InviteDTO() {
        // default constructor
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getHouseholdId() {
        return householdId;
    }

    public void setHouseholdId(Long householdId) {
        this.householdId = householdId;
    }

    @Override
    public String toString() {
        return "InviteDTO{" +
                "\n\tuserId=" + userId +
                ",\n\thouseholdId=" + householdId +
                "\n}";
    }
}
