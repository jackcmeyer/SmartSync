package model;

/**
 * Created by jack on 3/15/17.
 */
public class HouseholdServiceLookUpPOJO {

    /**
     * the household user look up id
     */
    private Long id;

    /**
     * the user id
     */
    private Long serviceId;

    /**
     * The household id
     */
    private Long householdId;

    public HouseholdServiceLookUpPOJO(Long id, Long serviceId, Long householdId) {
        this.id = id;
        this.serviceId = serviceId;
        this.householdId = householdId;
    }

    public HouseholdServiceLookUpPOJO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long userId) {
        this.serviceId = serviceId;
    }

    public Long getHouseholdId() {
        return householdId;
    }

    public void setHouseholdId(Long householdId) {
        this.householdId = householdId;
    }
}
