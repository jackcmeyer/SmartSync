package com.smartsync.controller;

import com.smartsync.dto.InviteDTO;
import com.smartsync.model.Invite;
import com.smartsync.service.InviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The Invite Controller
 */
@RestController
public class InviteController {

    @Autowired
    private InviteService inviteService;

    public InviteController() {

    }

    /**
     * Returns all of the invites
     * @return the response entity with all of the invites
     */
    @RequestMapping(method = RequestMethod.GET, value = "/", produces = "application/json")
    public ResponseEntity getAllInvites() {
        return ResponseEntity.ok(this.inviteService.getAllInvites());
    }

    /**
     * Gets an invite by id
     * @param id the id to find by
     * @return the invite
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = "application/json")
    public ResponseEntity getInvite(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(this.inviteService.getInvite(id));
    }


    /**
     * Adds a new household
     * @param inviteDTO the invite data transfer object
     * @return the invite that was created
     */
    @RequestMapping(method = RequestMethod.POST, value = "/", produces = "application/json")
    public ResponseEntity addInvite(@RequestBody InviteDTO inviteDTO) {

        // TODO validation

        Invite invite = this.inviteService.addInvite(inviteDTO);

        return ResponseEntity.ok(invite);
    }

    /**
     * Deletes an invite by id
     * @param id the id to delete
     * @return the deleted invite
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", produces = "application/json")
    public ResponseEntity deleteInvite(@PathVariable("id") Long id) {
        Invite invite = this.inviteService.deleteInvite(id);

        // TODO validation

        return ResponseEntity.ok(invite);
    }

    /**
     * Returns all of the invites for a user
     * @param userId the user to find invites for
     * @return the response entity with all of the invites for user
     */
    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}", produces = "application/json")
    public ResponseEntity getAllInvitesForUser(@PathVariable("userId") Long userId) {
        List<Invite> invites = this.inviteService.getAllInvitesForUser(userId);

        return ResponseEntity.ok(invites);
    }

    /**
     * Returns all of the invites for a household
     * @param householdId the household to find invites for
     * @return the response entity with all of the invites for household
     */
    @RequestMapping(method = RequestMethod.GET, value = "/households/{householdId}", produces = "application/json")
    public ResponseEntity getAllInvitesForHousehold(@PathVariable("householdId") Long householdId) {
        List<Invite> invites = this.inviteService.getAllInvitesForHousehold(householdId);

        return ResponseEntity.ok(invites);
    }

    /**
     * Deletes all invites for a user
     * @param userId the user to delete invites for
     * @return the response entity with all of the deleted invites
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{userId}", produces = "application/json")
    public ResponseEntity deleteAllInvitesForUser(@PathVariable("userId") Long userId) {
        List<Invite> invites = this.inviteService.deleteAllInvitesForUser(userId);

        return ResponseEntity.ok(invites);
    }

    /**
     * Deletes all invites for household
     * @param householdId the household to delete invites for
     * @return the response entity with all of the deleted invites
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/households/{householdId}", produces = "application/json")
    public ResponseEntity deleteAllInvitesForHousehold(@PathVariable("householdId") Long householdId) {
        List<Invite> invites = this.inviteService.deleteAllInvitesForHousehold(householdId);

        return ResponseEntity.ok(invites);
    }
}
