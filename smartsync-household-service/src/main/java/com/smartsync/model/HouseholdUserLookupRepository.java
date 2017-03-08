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
    List<HouseholdUserLookup> findHouseholdUserLookupByHouseholdId(String householdId);

    /**
     * Finds a HouseHOld obejct by user id. This will inherently give us all of the households that a given user is a
     * part of.
     *
     * @param userId the user id to to find by
     *
     * @return the list of household user objects
     */
    List<HouseholdUserLookup> findHouseholdUserLookupByUserId(String userId);
}


