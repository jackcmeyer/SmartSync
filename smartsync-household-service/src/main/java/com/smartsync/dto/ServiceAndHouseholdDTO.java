package com.smartsync.dto;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The add user dto, contains a user id and household id
 */
public class ServiceAndHouseholdDTO {

    /**
     * The household id that we are adding a user to
     */
    private Long householdId;

    /**
     * The user id we are adding to a household
     */
    private Long serviceId;

    public ServiceAndHouseholdDTO(Long householdId, Long serviceId) {
        this.householdId = householdId;
        this.serviceId = serviceId;
    }

    public ServiceAndHouseholdDTO() {
    }

    public Long getHouseholdId() {
        return householdId;
    }

    public void setHouseholdId(Long householdId) {
        this.householdId = householdId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }
}
