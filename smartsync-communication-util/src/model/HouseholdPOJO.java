package model;

/**
 * @author Jack Meyer
 *
 * The Household Data Transfer Object
 */
public class HouseholdPOJO {

    /**
     * The id of the household
     */
    private Long householdId;

    /**
     * The nickname of the household
     */
    private String householdName;

    /**
     * The userId of the owner of the household
     */
    private String ownerId;

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
    private String lastUpdated;

    /**
     * The created date
     */
    private String created;

    public HouseholdPOJO(Long householdId, String householdName, String ownerId, String firstAddressLine,
                         String secondAddressLine, String city, String state, int zipCode, String lastUpdated,
                         String created) {
        this.householdId = householdId;
        this.householdName = householdName;
        this.ownerId = ownerId;
        this.firstAddressLine = firstAddressLine;
        this.secondAddressLine = secondAddressLine;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.lastUpdated = lastUpdated;
        this.created = created;
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

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
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

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "HouseholdPOJO{" +
                "\n\thouseholdId=" + householdId +
                ",\n\thouseholdName='" + householdName + '\'' +
                ",\n\townerId='" + ownerId + '\'' +
                ",\n\tfirstAddressLine='" + firstAddressLine + '\'' +
                ",\n\tsecondAddressLine='" + secondAddressLine + '\'' +
                ",\n\tcity='" + city + '\'' +
                ",\n\tstate='" + state + '\'' +
                ",\n\tzipCode=" + zipCode +
                ",\n\tlastUpdated=" + lastUpdated +
                ",\n\tcreated=" + created +
                "\n}";
    }
}


