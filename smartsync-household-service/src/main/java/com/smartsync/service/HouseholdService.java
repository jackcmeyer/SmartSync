package com.smartsync.service;

import com.smartsync.model.Household;
import com.smartsync.model.HouseholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The household service
 */
@Component
public class HouseholdService {

    @Autowired
    private HouseholdRepository householdRepository;

    @Autowired
    private HouseholdUserLookupService householdUserLookupService;

    public HouseholdService() {

    }

    /**
     * Gets all of the households that are apart of the application
     *
     * @return all of the households
     */
    public List<Household> getAllHouseholds() {
        return this.householdRepository.findAll();
    }

    /**
     * Gets the household by id
     *
     * @param id the id to find
     *
     * @return the household
     */
    public Household getHouseHoldById(Long id) {
        return this.householdRepository.findByHouseholdId(id);
    }

    /**
     * Add a household to the database
     *
     * @param household the household to add
     *
     * @return the household that was added
     */
    public Household addHousehold(Household household) {
        Household h = this.householdRepository.save(household);

        return h;
    }

    /**
     * Delets the household with the current id.
     * @param id
     * @return
     */
    public Household deleteHousehold(Long id) {
        Household household = this.householdRepository.findByHouseholdId(id);

        if(household == null) {
            return null;
        }

        else {
            this.householdRepository.delete(household);

            this.householdUserLookupService.removeAllHouseholds(id);

            return household;
        }


    }
}
