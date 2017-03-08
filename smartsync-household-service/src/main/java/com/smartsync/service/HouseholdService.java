package com.smartsync.service;

import com.smartsync.model.HouseholdRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The household service
 */
public class HouseholdService {

    @Autowired
    private HouseholdRepository householdRepository;

    public HouseholdService() {

    }
}
