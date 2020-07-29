package com.mccarthy.david.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Model class to store customer information.
 */
public class Customer {
    @JsonProperty("user_id")
    private int userId;
    @JsonProperty
    private BigDecimal latitude;
    @JsonProperty
    private BigDecimal longitude;
    @JsonProperty
    private String name;

    /**
     * Get this customers user id.
     * @return Customers user id.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Set the user id for this customer.
     * @param userId Customers user id.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Get the latitude of this customers address.
     * @return Latitude of this customers address.
     */
    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return this.userId + ": " + this.name;
    }
}
