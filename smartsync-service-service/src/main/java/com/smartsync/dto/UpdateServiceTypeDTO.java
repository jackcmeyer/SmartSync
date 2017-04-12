package com.smartsync.dto;

import java.util.Date;

/**
 * @author Jack Meyer
 *
 * The Service DTO
 */
public class UpdateServiceTypeDTO {

    /**
     * The service's int
     */
    private Long serviceId;

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


    private String component;

    /**
     * Constructor which uses parameters to create the new user
     * @param name
     * @param description
     * @param isActive
     */
    public UpdateServiceTypeDTO(String name, String description, int isActive) {
        this.name = name;
        this.description = description;
        this.isActive = isActive;
    }

    public UpdateServiceTypeDTO() {
        // empty constructor
    }

    public Long getServiceTypeId() {
        return serviceId;
    }

    public String getDescription(){ return description;}

    public void setDescription(String description){ this.description = description;}

    public String getName(){ return name; }

    public void setName(String name){ this.name = name; }

    public int getIsActive(){ return this.isActive; }

    public void setIsActive(int isActive ){ this.isActive = isActive; }

    public String getComponent(){ return component; }

    public void setComponent(String component){ this.component = component; }


}
