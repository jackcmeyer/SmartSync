package com.smartsync.model;

import com.smartsync.dto.InviteDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The Invite Model
 */
@Entity
public class Invite {

    /**
     * The invite id
     */
    @Id @GeneratedValue
    private Long id;

    /**
     * The user id
     */
    private Long userId;

    /**
     * The household id
     */
    private Long householdId;

    /**
     * boolean for if the invite is accepted or not
     */
    private boolean accepted;

    /**
     * The date the invite was created
     */
    private Date created;

    /**
     * The date the invite was last updated
     */
    private Date lastUpdated;

    public Invite(Long userId, Long householdId) {
        this.userId = userId;
        this.householdId = householdId;
        this.accepted = false; // an invite when created is not accepted.
        this.created = new Date();
        this.lastUpdated = new Date();
    }

    public Invite(InviteDTO invite) {
        this.userId = invite.getUserId();
        this.householdId = invite.getHouseholdId();
        this.accepted = false;
        this.created = new Date();
        this.lastUpdated = new Date();
    }

    public Invite() {
        // default constructor
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getHouseholdId() {
        return householdId;
    }

    public void setHouseholdId(Long householdId) {
        this.householdId = householdId;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return "Invite{" +
                "\n\tid=" + id +
                ", \n\tuserId=" + userId +
                ", \n\thouseholdId=" + householdId +
                ", \n\taccepted=" + accepted +
                ", \n\tcreated=" + created +
                ", \n\tlastUpdated=" + lastUpdated +
                "\n}";
    }
}
