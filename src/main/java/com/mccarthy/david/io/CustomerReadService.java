package com.mccarthy.david.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mccarthy.david.error.ErrorService;
import com.mccarthy.david.error.InputException;
import com.mccarthy.david.model.Customer;
import com.mccarthy.david.validation.CustomerValidationService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Service to read customer data from a given file.
 */
public class CustomerReadService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final CustomerValidationService customerValidationService;
    private final ErrorService errorService;

    /**
     * Constructor
     *
     * @param customerValidationService Service to validate customer input has been set in each customer object.
     */
    public CustomerReadService(CustomerValidationService customerValidationService, ErrorService errorService) {
        this.customerValidationService = customerValidationService;
        this.errorService = errorService;
    }

    /**
     * Get a list of customers from the given customer data file.
     *
     * @param customerListFileName Customer data file name.
     * @return List of customers extracted from the file.
     * @throws Exception Exception to be handled above.
     */
    public List<Customer> getCustomersFromFile(String customerListFileName) throws Exception {
        try {
            Path customerDataFile = Paths.get(customerListFileName);
            List<String> customerListAsString = Files.readAllLines(customerDataFile);
            return mapToListOfCustomers(customerListAsString);
        } catch (IOException e) {
            System.out.println("IOException when trying to read file: " + customerListFileName);
            throw e;
        }
    }

    /**
     * Map the list of customer details as a json string to a list of customers.
     *
     * @param customerListAsString List of customer details as json string.
     * @return List of customers.
     */
    protected List<Customer> mapToListOfCustomers(List<String> customerListAsString) throws Exception {
        List<Customer> customers = new ArrayList<>();
        List<String> mappingErrors = new ArrayList<>();
        for (String customerString : customerListAsString) {
            try {
                customers.add(mapCustomer(customerString));
            } catch (IOException ioe) {
                //If we get an exception mapping the customer, add an error and move on to the next one.
                mappingErrors.add(customerString);
            }
        }

        try {
            customerValidationService.validateCustomers(customers);
        } catch (InputException e) {
            e.setInputErrors(mappingErrors);
            errorService.logInputErrors(e);
            throw e;
        }

        if (!mappingErrors.isEmpty()) {
            InputException inputException = new InputException(mappingErrors);
            errorService.logInputErrors(inputException);
            throw inputException;
        }

        return customers;
    }

    /**
     * Map a json string customer data entry to a Custome object.
     *
     * @param customerData Customer data as a json formatted string.
     * @return Customer object.
     */
    protected Customer mapCustomer(String customerData) throws IOException {
        return objectMapper.readValue(customerData, Customer.class);
    }
}
