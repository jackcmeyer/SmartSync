package com.smartsync.dto;

/**
 * @author Jack Meyer (jackcmeyer@gmail.com)
 *
 * The user preferences dto
 */
public class UserPreferencesDTO {

    /**
     * The user the preferences apply to
     */
    private Long userId;

    /**
     * The name of the preference
     */
    private String name;

    /**
     * The primary color
     */
    private String primaryColor;

    /**
     * The secondary color
     */
    private String secondaryColor;

    /**
     * The accent color
     */
    private String accentColor;

    /**
     * The neutral light color
     */
    private String neutralLightColor;

    /**
     * the neutral dark color
     */
    private String neutralDarkColor;

    public UserPreferencesDTO(Long userId, String name, String primaryColor, String secondaryColor, String accentColor,
                              String neutralLightColor, String neutralDarkColor) {
        this.userId = userId;
        this.name = name;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.accentColor = accentColor;
        this.neutralLightColor = neutralLightColor;
        this.neutralDarkColor = neutralDarkColor;
    }

    public UserPreferencesDTO() {
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

    public String getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }

    public String getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(String secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public String getAccentColor() {
        return accentColor;
    }

    public void setAccentColor(String accentColor) {
        this.accentColor = accentColor;
    }

    public String getNeutralLightColor() {
        return neutralLightColor;
    }

    public void setNeutralLightColor(String neutralLightColor) {
        this.neutralLightColor = neutralLightColor;
    }

    public String getNeutralDarkColor() {
        return neutralDarkColor;
    }

    public void setNeutralDarkColor(String neutralDarkColor) {
        this.neutralDarkColor = neutralDarkColor;
    }
}
