package com.smartsync.service;

import com.smartsync.model.HouseholdUserLookupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
