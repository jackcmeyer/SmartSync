package com.smartsync.model;

import com.smartsync.dto.HouseholdDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The household model
 */
@Entity
public class Household {

    /**
     * The id of the household
     */
    @Id @GeneratedValue
    private Long householdId;

    /**
     * The nickname of the household
     */
    private String householdName;


    /**
     * The userId of the owner of the household
     */
    private Long ownerId;

    /**
     * The first address line
     *
     * Ex. 111 Main ST SW
     */
    private String firstAddressLine;

    /**
     * The second address line
     *
     * Ex. Apt 111
     */
    private String secondAddressLine;

    /**
     * The city that the household is in
     *
     * Ex. Ames
     */
    private String city;

    /**
     * The state that the household is in
     * Ex. IA
     */
    private String state;

    /**
     * The zip code of the household
     */
    private int zipCode;

    /**
     * The last updated date
     */
    private Date lastUpdated;

    /**
     * The created date
     */
    private Date created;

    public Household(String householdName, Long ownerId, String firstAddressLine,
                     String secondAddressLine, String city, String state, int zipCode) {

        this.householdName = householdName;
        this.ownerId = ownerId;
        this.firstAddressLine = firstAddressLine;
        this.secondAddressLine = secondAddressLine;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.created = new Date();
        this.lastUpdated = new Date();
    }

    public Household(HouseholdDTO householdTO) {
        this.householdName = householdTO.getHouseholdName();
        this.ownerId = householdTO.getOwnerId();
        this.firstAddressLine = householdTO.getFirstAddressLine();
        this.secondAddressLine = householdTO.getSecondAddressLine();
        this.city = householdTO.getCity();
        this.state = householdTO.getState();
        this.zipCode = householdTO.getZipCode();
        this.created = new Date();
        this.lastUpdated = new Date();
    }

    public Household() {
        // default constructor
    }

    public Long getHouseholdId() {
        return householdId;
    }

    public void setHouseholdId(Long householdId) {
        this.householdId = householdId;
    }

    public String getHouseholdName() {
        return householdName;
    }

    public void setHouseholdName(String householdName) {
        this.householdName = householdName;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getFirstAddressLine() {
        return firstAddressLine;
    }

    public void setFirstAddressLine(String firstAddressLine) {
        this.firstAddressLine = firstAddressLine;
    }

    public String getSecondAddressLine() {
        return secondAddressLine;
    }

    public void setSecondAddressLine(String secondAddressLine) {
        this.secondAddressLine = secondAddressLine;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
