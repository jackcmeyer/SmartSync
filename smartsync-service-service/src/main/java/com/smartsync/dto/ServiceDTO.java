package com.smartsync.dto;

import java.util.Date;

/**
 * @author Jack Meyer
 *
 * The Service DTO
 */
public class ServiceDTO {

    /**
     * The service's int
     */
    private Long serviceId;

    /**
     * The serviceTypeId for the service
     */
    private Long serviceTypeId;

    /**
     * The service's name
     */
    private String name;

    /**
     * The description of the service
     */
    private String description;

    /**
     * The user's family name
     */
    private int isActive;

    /**
     * The user's image url. This is a link to their google profile picture
     */
    private int wide;

    /**
     * The user's email. This is the google email.
     */
    private int tall;

    /**
     * The date that the user was created
     */
    private Date created;

    /**
     * The date that the user was last updated
     */
    private Date lastUpdated;

    /**
     * Constructor which uses parameters to create the new user
     * @param name
     * @param description
     * @param isActive
     * @param wide
     * @param tall
     */
    public ServiceDTO(Long serviceTypeId, String name, String description, int isActive, int wide, int tall) {
        this.serviceTypeId = serviceTypeId;
        this.name = name;
        this.description = description;
        this.isActive = isActive;
        this.wide = wide;
        this.tall = tall;
    }

    public ServiceDTO() {
        // empty constructor
    }

    public Long getServiceId() {
        return serviceId;
    }

    public Long getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(Long serviceTypeId) { this.serviceTypeId = serviceTypeId; }

    public String getDescription(){ return description;}

    public void setDescription(String description){ this.description = description;}

    public String getName(){ return name; }

    public void setName(String name){ this.name = name; }

    public int getIsActive(){ return this.isActive; }

    public void setIsActive(int isActive ){ this.isActive = isActive; }

    public int getWide(){ return this.wide; }

    public void setWide(int wide){ this.wide = wide; }

    public int getTall(){ return this.tall; }

    public void setTall(int tall){ this.tall = tall; }

    public Date getCreated(){ return this.created; }

    public void setCreated(Date created){ this.created = created; }

    public Date getLastUpdated(){ return this.lastUpdated; }

    public void setLastUpdated(Date lastUpdated){ this.lastUpdated = lastUpdated; }


    @Override
    public String toString() {
        return "ServiceDTO {" +
                "\n\tname='" + name + '\'' +
                ", \n\tdescription='" + description + '\'' +
                ", \n\tisActive='" + isActive + '\'' +
                ", \n\twide='" + wide + '\'' +
                ", \n\ttall='" + tall + '\'' +
                "\n}";
    }
}
