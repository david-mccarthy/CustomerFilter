package com.mccarthy.david.utils;

import com.mccarthy.david.model.Customer;

/**
 * Helper class to allow for reuse of test code.
 */
public class TestHelper {
    public static final String CUSTOMER_DATA_FORMAT = "{\"latitude\": \"%s\", \"user_id\": %s, \"name\": \"%s\", \"longitude\": \"%s\"}";

    public static String createTestCustomerString(String userId, String customerName, double latitude, double longitude) {
        return createTestCustomerString(userId, customerName, Double.toString(latitude), Double.toString(longitude));
    }

    public static String createTestCustomerString(String userId, String customerName, String latitude, String longitude) {
        return String.format(CUSTOMER_DATA_FORMAT, latitude, userId, customerName, longitude);
    }
    public static Customer createTestCustomer(int userId, String customerName, double latitude, double longitude) {
        Customer customer = new Customer();
        customer.setUserId(userId);
        customer.setName(customerName);
        customer.setLatitude(latitude);
        customer.setLongitude(longitude);

        return customer;
    }
}
