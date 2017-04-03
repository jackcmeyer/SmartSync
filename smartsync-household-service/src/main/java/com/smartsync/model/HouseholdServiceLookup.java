package com.smartsync.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * Tthe Household User Lookup table, this will store information about what users belong to the household.
 */
@Entity
public class HouseholdServiceLookup {

    @Id @GeneratedValue
    private Long id;

    /**
     * The user id
     */
    private Long serviceId;

    /**
     * The household id
     */
    private Long householdId;

    public HouseholdServiceLookup(Long serviceId, Long householdId) {
        this.serviceId = serviceId;
        this.householdId = householdId;
    }

    public HouseholdServiceLookup() {
        // default constructor
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getServiceId() {
        return this.serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Long getHouseholdId() {
        return householdId;
    }

    public void setHouseholdId(Long householdId) {
        this.householdId = householdId;
    }
}
