package com.smartsync.service;

import com.smartsync.dto.InviteDTO;
import com.smartsync.model.Invite;
import com.smartsync.model.InviteRepository;
import communication.HouseholdServiceCommunication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
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
     * Accepts an invite. It should delete other invites for a user and add them to a household.
     * @param id the invite id
     * @return the invite that was accepted
     */
    public Invite acceptInvite(Long id) {

        // get invite and and delete, don't need it anymore
        Invite invite = this.inviteRepository.findById(id);
        invite.setAccepted(true);
        this.inviteRepository.delete(invite);

        // get other invites for users, and delete
        List<Invite> userInvites = this.inviteRepository.findByUserId(invite.getUserId());
        this.inviteRepository.delete(userInvites);

        // add user to household
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("userId", Long.toString(invite.getUserId()));
        parameters.put("householdId", Long.toString(invite.getHouseholdId()));
        HouseholdServiceCommunication householdServiceCommunication = new HouseholdServiceCommunication();
        householdServiceCommunication.addUserToHousehold(parameters);

        return invite;
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
