package com.mccarthy.david.validation;

import com.mccarthy.david.error.InputException;
import com.mccarthy.david.model.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service to validate that customer objects are correctly set up.
 */
public class CustomerValidationService {
    public static final String CUSTOMER_NAME_IS_MISSING = "Customer name is missing.";
    public static final String LONGITUDE_IS_MISSING = "Longitude is missing.";
    public static final String LATITUDE_IS_MISSING = "Latitude is missing.";
    public static final String ID_IS_MISSING = "Id is missing,";

    /**
     * Validate the list of customers to ensure they are valid before processing.
     *
     * @param customers List of customers.
     * @throws InputException Input exception containing details of the customers containing bad data.
     */
    public void validateCustomers(List<Customer> customers) throws InputException {
        Map<Customer, List<String>> errors = new HashMap<>();
        for (Customer customer : customers) {
            validateCustomer(customer, errors);
        }
        if (!errors.isEmpty()) {
            throw new InputException(errors);
        }
    }

    /**
     * Validate a customer object to ensure it is valid before processing.
     *
     * @param customer Customer to validate.
     * @param errors   Map of errors to be returned containing all customer errors.
     */
    protected void validateCustomer(Customer customer, Map<Customer, List<String>> errors) {
        List<String> customerErrors = new ArrayList<>();

        if (customer.getUserId() == null) {
            customerErrors.add(ID_IS_MISSING);
        }

        if (customer.getLatitude() == null) {
            customerErrors.add(LATITUDE_IS_MISSING);
        }

        if (customer.getLongitude() == null) {
            customerErrors.add(LONGITUDE_IS_MISSING);
        }

        if (customer.getName() == null || "".equals(customer.getName())) {
            customerErrors.add(CUSTOMER_NAME_IS_MISSING);
        }

        if (!customerErrors.isEmpty()) {
            errors.put(customer, customerErrors);
        }
    }
}
