package com.smartsync.service;

import com.smartsync.model.HouseholdUserLookupRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The household user look up service
 */
public class HouseholdUserLookupService {

    @Autowired
    private HouseholdUserLookupRepository householdUserLookupRepository;

    public HouseholdUserLookupService() {

    }
}
