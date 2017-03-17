package com.smartsync.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The Invite Repository
 */
public interface InviteRepository extends JpaRepository<Invite, Long> {

    /**
     * Finds an invite by id
     * @param id the id to find by
     * @return the invite
     */
    Invite findById(Long id);

    /**
     * Finds all of the invites for a user
     * @param userId the user id to find by
     * @return the list of all of the invites for a user
     */
    List<Invite> findByUserId(Long userId);

    /**
     * Finds all of the invites for a household
     * @param householdId the household id to find by
     * @return the list of all of the invites for a household
     */
    List<Invite> findByHouseholdId(Long householdId);

}
