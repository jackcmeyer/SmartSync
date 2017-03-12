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
public class HouseholdUserLookup {

    @Id @GeneratedValue
    private Long id;

    /**
     * The user id
     */
    private Long userId;

    /**
     * The household id
     */
    private Long householdId;

    public HouseholdUserLookup(Long userId, Long householdId) {
        this.userId = userId;
        this.householdId = householdId;
    }

    public HouseholdUserLookup() {
        // default constructor
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return this.userId;
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
}
