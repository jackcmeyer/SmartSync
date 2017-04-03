package com.smartsync.service;

import com.smartsync.model.HouseholdServiceLookup;
import com.smartsync.model.HouseholdServiceLookupRepository;
import communication.ServiceServiceCommunication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import model.ServicePOJO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gregory on 4/2/17.
 */
@Component
public class HouseholdServiceLookupService {

    @Autowired
    private HouseholdServiceLookupRepository householdServiceLookupRepository;

    public HouseholdServiceLookupService() {

    }

    /**
     * Adds a user to the household
     * @param userId the user id to add
     * @param householdId the household to add the user to
     * @return the household user look up
     */
    public HouseholdServiceLookup addServiceToHouseHold(Long serviceId, Long householdId) {

        HouseholdServiceLookup householdServiceLookup = new HouseholdServiceLookup(serviceId, householdId);
        HouseholdServiceLookup savedHouseholdServiceLookup = this.householdServiceLookupRepository.save(householdServiceLookup);

        return savedHouseholdServiceLookup;

    }

    /**
     * Gets all of the user ids in a household
     * @param id the household id
     * @return a list of users a part of the household
     */
    public List<ServicePOJO> getHouseholdServices(Long id) {
        List<HouseholdServiceLookup> householdServiceLookups =
                this.householdServiceLookupRepository.findByHouseholdId(id);


        List<ServicePOJO> services = new ArrayList<>();
        for(HouseholdServiceLookup householdServiceLookup : householdServiceLookups) {
            ServiceServiceCommunication serviceServiceCommunication = new ServiceServiceCommunication();
            services.add(serviceServiceCommunication.getService(householdServiceLookup.getServiceId()));
        }

        return services;
    }

    /**
     * Removes a user from a household
     * @param serviceId the user to remove
     * @param householdId the household to remove from
     * @return the household user look up object that was removed
     */
    public HouseholdServiceLookup removeServiceFromHousehold(Long serviceId, Long householdId) {

        HouseholdServiceLookup householdServiceLookup =
                this.householdServiceLookupRepository.findByServiceIdAndHouseholdId(serviceId, householdId);

        this.householdServiceLookupRepository.delete(householdServiceLookup);

        return householdServiceLookup;
    }

    /**
     * Removes all of the household user lookups with the household id. This will be useful when a household is deleted
     * @param householdId the household id to remove
     * @return the list of the household user lookups that were removed.
     */
    public List<HouseholdServiceLookup> removeAllHouseholds(Long householdId) {
        List<HouseholdServiceLookup> households =
                this.householdServiceLookupRepository.findByHouseholdId(householdId);

        System.out.println(households);

        for(HouseholdServiceLookup householdServiceLookup : households) {
            this.householdServiceLookupRepository.delete(householdServiceLookup);
        }

        return households;

    }
}


