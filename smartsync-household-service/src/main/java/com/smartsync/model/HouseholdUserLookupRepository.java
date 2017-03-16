package com.smartsync.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The HouseholdUserLookUp Repository
 */
public interface HouseholdUserLookupRepository extends JpaRepository<HouseholdUserLookup, String> {

    /**
     * Finds a HouseholdLookup object by household id. This will inherently give us all of the user ids that are a part
     * of the household as well.
     *
     * @param householdId the household id
     *
     * @return the list of household user objects
     */
    List<HouseholdUserLookup> findByHouseholdId(Long householdId);

    /**
     * Finds a HouseHold object by user id. This will inherently give us all of the households that a given user is a
     * part of.
     *
     * @param userId the user id to to find by
     *
     * @return the list of household user objects
     */
    HouseholdUserLookup findByUserId(Long userId);

    /**
     * Finds the HouseHoldUserLookup that is associated with the household and the user.
     * @param userId the user id to find by
     * @param householdId the household to find by
     * @return the household user lookup
     */
    HouseholdUserLookup findByUserIdAndHouseholdId(Long userId, Long householdId);
}


