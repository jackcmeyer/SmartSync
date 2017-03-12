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
     * @param id
     * @return
     */
    public List<UserPOJO> getUsersInHousehold(Long id) {
        List<HouseholdUserLookup> householdUserLookups =
                this.householdUserLookupRepository.findHouseholdUserLookupByHouseholdId(id);


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

        HouseholdUserLookup householdUserLookup = this.householdUserLookupRepository.findHouseholdUserLookupByUserId(id);
        return householdUserLookup;
    }
}
