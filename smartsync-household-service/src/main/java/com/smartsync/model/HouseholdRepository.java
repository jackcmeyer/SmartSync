package com.smartsync.model;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jack Meyer
 *
 * The Household Repository
 */
public interface HouseholdRepository extends JpaRepository<Household, String> {

    /**
     * Finds a household by its id
     *
     * @param id the id to find by
     *
     * @return a household
     */
    Household findByHouseholdId(Long id);

}
