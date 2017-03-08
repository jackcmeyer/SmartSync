package com.smartsync.model;

import javax.persistence.Entity;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * Tthe Household User Lookup table, this will store information about what users belong to the household.
 */
@Entity
public class HouseholdUserLookup {

    /**
     * The user id
     */
    private String userId;

    /**
     * The household id
     */
    private String householdId;

    public HouseholdUserLookup(String userId, String householdId) {
        this.userId = userId;
        this.householdId = householdId;
    }

    public HouseholdUserLookup() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHouseholdId() {
        return householdId;
    }

    public void setHouseholdId(String householdId) {
        this.householdId = householdId;
    }
}
