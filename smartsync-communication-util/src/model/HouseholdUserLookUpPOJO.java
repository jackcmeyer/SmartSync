package model;

/**
 * Created by jack on 3/15/17.
 */
public class HouseholdUserLookUpPOJO {

    /**
     * the household user look up id
     */
    private Long id;

    /**
     * the user id
     */
    private Long userId;

    /**
     * The household id
     */
    private Long householdId;

    public HouseholdUserLookUpPOJO(Long id, Long userId, Long householdId) {
        this.id = id;
        this.userId = userId;
        this.householdId = householdId;
    }

    public HouseholdUserLookUpPOJO() {

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
}
