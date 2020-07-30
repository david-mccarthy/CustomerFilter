package com.mccarthy.david.utils;

import com.mccarthy.david.model.Customer;

/**
 * Helper class to allow for reuse of test code.
 */
public class TestHelper {
    public static final String CUSTOMER_DATA_FORMAT = "{\"latitude\": \"%f\", \"user_id\": %s, \"name\": \"%s\", \"longitude\": \"%f\"}";

    public static String createTestCustomerString(String userId, String customerName, double latitude, double longitude) {
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
