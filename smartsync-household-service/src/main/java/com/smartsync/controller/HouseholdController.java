package com.smartsync.controller;

import com.smartsync.service.HouseholdService;
import com.smartsync.service.HouseholdUserLookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jack Meyer
 *
 * The Household Controller
 */
@RestController
public class HouseholdController {

    @Autowired
    private HouseholdService householdService;

    @Autowired
    private HouseholdUserLookupService householdUserLookupService;

    public HouseholdController() {
        
    }
}
