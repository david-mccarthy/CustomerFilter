package com.mccarthy.david.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Comparator;

/**
 * Model class to store customer information.
 */
public class Customer {
    @JsonProperty("user_id")
    private int userId;
    @JsonProperty
    private double latitude;
    @JsonProperty
    private double longitude;
    @JsonProperty
    private String name;

    /**
     * Get this customers user id.
     *
     * @return Customers user id.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Set the user id for this customer.
     *
     * @param userId Customers user id.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Get the latitude of this customers address.
     *
     * @return Latitude of this customers address.
     */
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.userId + ": " + this.name;
    }

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
