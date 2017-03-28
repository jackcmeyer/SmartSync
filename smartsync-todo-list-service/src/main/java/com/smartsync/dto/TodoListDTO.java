package com.smartsync.dto;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The to do list dto
 */
public class TodoListDTO {

    /**
     * The user id
     */
    private Long userId;

    /**
     * The name of the to do list
     */
    private String name;

    public TodoListDTO(Long userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public TodoListDTO() {
        // default constructor
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TodoListDTO{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                '}';
    }
}
