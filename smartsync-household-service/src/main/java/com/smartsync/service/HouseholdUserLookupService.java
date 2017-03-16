package com.smartsync.service;

import com.smartsync.model.HouseholdUserLookup;
import com.smartsync.model.HouseholdUserLookupRepository;
import communication.UserServiceCommunication;
import model.UserPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The household user look up service
 */
@Component
public class HouseholdUserLookupService {

    @Autowired
    private HouseholdUserLookupRepository householdUserLookupRepository;

    public HouseholdUserLookupService() {

    }

    /**
     * Adds a user to the household
     * @param userId the user id to add
     * @param householdId the household to add the user to
     * @return the household user look up
     */
    public HouseholdUserLookup addUserToHouseHold(Long userId, Long householdId) {

        HouseholdUserLookup householdUserLookup = new HouseholdUserLookup(userId, householdId);
        HouseholdUserLookup savedHouseholdUserLookup = this.householdUserLookupRepository.save(householdUserLookup);

        return savedHouseholdUserLookup;

    }

    /**
     * Gets all of the user ids in a household
     * @param id the household id
     * @return a list of users a part of the household
     */
    public List<UserPOJO> getUsersInHousehold(Long id) {
        List<HouseholdUserLookup> householdUserLookups =
                this.householdUserLookupRepository.findByHouseholdId(id);


        List<UserPOJO> users = new ArrayList<>();
        for(HouseholdUserLookup householdUserLookup : householdUserLookups) {
            UserServiceCommunication userServiceCommunication = new UserServiceCommunication();
            users.add(userServiceCommunication.getUser(householdUserLookup.getUserId()));
        }

        return users;
    }

    /**
     * Returns the household user look up for the user id
     * @param id the user id to find
     * @return the household user look up object
     */
    public HouseholdUserLookup getHouseholdForUser(Long id) {

        HouseholdUserLookup householdUserLookup = this.householdUserLookupRepository.findByUserId(id);
        return householdUserLookup;
    }

    /**
     * Removes a user from a household
     * @param userId the user to remove
     * @param householdId the household to remove from
     * @return the household user look up object that was removed
     */
    public HouseholdUserLookup removeUserFromHousehold(Long userId, Long householdId) {

        HouseholdUserLookup householdUserLookup =
                this.householdUserLookupRepository.findByUserIdAndHouseholdId(userId, householdId);

        this.householdUserLookupRepository.delete(householdUserLookup);

        return householdUserLookup;
    }

    /**
     * Removes all of the household user lookups with the household id. This will be useful when a household is deleted
     * @param householdId the household id to remove
     * @return the list of the household user lookups that were removed.
     */
    public List<HouseholdUserLookup> removeAllHouseholds(Long householdId) {
        List<HouseholdUserLookup> households =
                this.householdUserLookupRepository.findByHouseholdId(householdId);

        System.out.println(households);

        for(HouseholdUserLookup householdUserLookup : households) {
            this.householdUserLookupRepository.delete(householdUserLookup);
        }

        return households;

    }
}
