package com.smartsync.dto;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The household to do list dto
 */
public class HouseholdTodoListDTO {

    /**
     * The household id
     */
    private Long householdId;

    /**
     * The name of the to do list
     */
    private String name;

    public HouseholdTodoListDTO(Long householdId, String name) {
        this.householdId = householdId;
        this.name = name;
    }

    public HouseholdTodoListDTO() {
    }

    public Long getHouseholdId() {
        return householdId;
    }

    public String getName() {
        return name;
    }
}
