package com.smartsync.model;

import com.smartsync.dto.ServiceDTO;
import com.smartsync.dto.ServiceTypeDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The Service Model
 */
@Entity
public class ServiceType {

    /**
     * The id for the serviceType.
     */
    @Id @GeneratedValue
    private Long serviceTypeId;

    private String name;

    private String description;

    private String component;

    int isActive;

    /**
     * Constructor which uses parameters to create the new user
     * @param serviceTypeId
     * @param name
     * @param description

     */
    public ServiceType(Long serviceTypeId, String name, String description, int isActive) {
        this.name = name;
        this.description = description;
        this.isActive = isActive;
    }

    /**
     * Constructor which service type the DTO to create the new service type
     * @param serviceTypeDTO
     */
    public ServiceType(ServiceTypeDTO serviceTypeDTO) {
        this.name = serviceTypeDTO.getName();
        this.component = serviceTypeDTO.getComponent();
        this.serviceTypeId = serviceTypeDTO.getServiceTypeId();
        this.description = serviceTypeDTO.getDescription();
        this.isActive = serviceTypeDTO.getIsActive();
    }

    public ServiceType() {
        // empty constructor
    }

    public Long getServiceTypeId() {
        return serviceTypeId;
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
