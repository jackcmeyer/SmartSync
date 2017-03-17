package com.smartsync.service;

import com.smartsync.dto.InviteDTO;
import com.smartsync.model.Invite;
import com.smartsync.model.InviteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The Invite Service
 */
@Component
public class InviteService {

    @Autowired
    private InviteRepository inviteRepository;

    public InviteService() {

    }

    /**
     * Gets all invites
     * @return the list of all invites
     */
    public List<Invite> getAllInvites() {
        return inviteRepository.findAll();
    }

    /**
     * Finds an invite by id
     * @param id the id to find by
     * @returnt the invite
     */
    public Invite getInvite(Long id) {
        return inviteRepository.findById(id);
    }

    /**
     * Gets all invites for a user
     * @param userId the user to get invites for
     * @return the list of invites for the user
     */
    public List<Invite> getAllInvitesForUser(Long userId) {
        return inviteRepository.findByUserId(userId);
    }

    /**
     * Gets all invites for a household
     * @param householdId the household id to get invites for
     * @return the list of invites for the household
     */
    public List<Invite> getAllInvitesForHousehold(Long householdId) {
        return inviteRepository.findByHouseholdId(householdId);
    }

    /**
     * Creates a new invite in the database
     * @param inviteDTO the invite data transfer object
     * @return the saved invite
     */
    public Invite addInvite(InviteDTO inviteDTO) {
        Invite invite = new Invite(inviteDTO);
        Invite savedInvite = this.inviteRepository.save(invite);
        return savedInvite;
    }

    /**
     * Deletes an invite from the database
     * @param id the id to delete
     * @return the deleted invite
     */
    public Invite deleteInvite(Long id) {
        Invite invite = this.inviteRepository.findById(id);
        this.inviteRepository.delete(invite);
        return invite;
    }

    /**
     * Deletes all invites for a user
     * @param userId the user to delete for
     * @return the list of all deleted invites
     */
    public List<Invite> deleteAllInvitesForUser(Long userId) {
        List<Invite> invites = this.inviteRepository.findByUserId(userId);
        this.inviteRepository.delete(invites);

        return invites;
    }

    /**
     * Deletes all invites for household
     * @param householdId the household to delete for
     * @return the list of all deleted invites
     */
    public List<Invite> deleteAllInvitesForHousehold(Long householdId) {
        List<Invite> invites = this.inviteRepository.findByHouseholdId(householdId);
        this.inviteRepository.delete(invites);

        return invites;
    }

}
