package com.smartsync.model;

import com.smartsync.dto.ServiceDTO;
import org.hibernate.type.descriptor.sql.TinyIntTypeDescriptor;

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
public class Service {

    /**
     * The id for the service.
     */
    @Id @GeneratedValue
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
    private Boolean isActive;

    /**
     * The user's image url. This is a link to their google profile picture
     */
    private Boolean wide;

    /**
     * The user's email. This is the google email.
     */
    private Boolean tall;

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
     * @param serviceId
     * @param name
     * @param description
     * @param isActive
     * @param wide
     * @param tall
     */
    public Service(Long serviceId, String name, String description, Boolean isActive, Boolean wide, Boolean tall) {
        this.serviceId = serviceId;
        this.name = name;
        this.description = description;
        this.isActive = isActive;
        this.wide = wide;
        this.tall = tall;
        this.created = new Date();
        this.lastUpdated = new Date();
    }

    /**
     * Constructor which users the DTO to create the new user
     * @param serviceDTO
     */
    public Service(ServiceDTO serviceDTO) {
        this.name = serviceDTO.getName();
        this.description = serviceDTO.getDescription();
        this.isActive = serviceDTO.getIsActive();
        this.wide = serviceDTO.getWide();
        this.tall = serviceDTO.getTall();
        this.created = new Date();
        this.lastUpdated = new Date();

    }

    public Service() {
        // empty constructor
    }

    public Long getServiceId() {
        return serviceId;
    }

    public String getDescription(){ return description;}

    public void setDescription(String description){ this.description = description;}

    public String getName(){ return name; }

    public void setName(String name){ this.name = name; }

    public Boolean getIsActive(){ return this.isActive; }

    public void setIsActive(Boolean isActive ){ this.isActive = isActive; }

    public Boolean getWide(){ return this.wide; }

    public void setWide(Boolean wide){ this.wide = wide; }

    public Boolean getTall(){ return this.tall; }

    public void setTall(Boolean tall){ this.tall = tall; }

    public Date getCreated(){ return this.created; }

    public void setCreated(Date created){ this.created = created; }

    public Date getLastUpdated(){ return this.lastUpdated; }

    public void setLastUpdated(Date lastUpdated){ this.lastUpdated = lastUpdated; }
}
