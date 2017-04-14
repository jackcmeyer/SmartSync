package model;

import java.util.Date;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The Service Model
 */
public class ServicePOJO {

    /**
     * The id for the service.
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
    private String created;

    /**
     * The date that the user was last updated
     */
    private String lastUpdated;


    /**
     * Constructor which uses parameters to create the new user
     * @param serviceId
     * @param name
     * @param description
     * @param isActive
     * @param wide
     * @param tall
     */
    public ServicePOJO(Long serviceId, Long serviceTypeId, String name, String description, int isActive, int wide, int tall, String created, String lastUpdated) {
        this.serviceId = serviceId;
        this.serviceTypeId = serviceTypeId;
        this.name = name;
        this.description = description;
        this.isActive = isActive;
        this.wide = wide;
        this.tall = tall;
        this.created = created;
        this.lastUpdated = lastUpdated;
    }

    public ServicePOJO() {
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

    public String getCreated(){ return this.created; }

    public void setCreated(String created){ this.created = created; }

    public String getLastUpdated(){ return this.lastUpdated; }

    public void setLastUpdated(String lastUpdated){ this.lastUpdated = lastUpdated; }
}
