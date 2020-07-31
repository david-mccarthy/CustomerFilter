package com.mccarthy.david.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Comparator;

/**
 * Model class to store customer information.
 */
public class Customer {
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("latitude")
    private Double latitude;
    @JsonProperty("longitude")
    private Double longitude;
    @JsonProperty("name")
    private String name;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameAndId() {
        return this.name + ": " + this.userId;
    }

    @Override
    public String toString() {
        return this.userId + ": " + this.name + " (" + this.latitude + ", " + this.longitude + ")";
    }

    /**
     * Get a comparotor that allows us to sort a list of customers.
     * @return Comparator for customers.
     */
    public static Comparator<Customer> getComparator() {
        return new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                if (o1.getUserId() < o2.getUserId()) {
                    return -1;
                } else if (o1.getUserId() == o2.getUserId()) {
                    return 0;
                }
                return 1;
            }
        };
    }
}
