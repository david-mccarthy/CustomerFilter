package com.mccarthy.david.controller;

import com.mccarthy.david.error.ErrorService;
import com.mccarthy.david.error.InputException;
import com.mccarthy.david.io.CustomerReadService;
import com.mccarthy.david.io.OutputService;
import com.mccarthy.david.model.Customer;
import com.mccarthy.david.services.DistanceFilterService;
import com.mccarthy.david.validation.CustomerValidationService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller class to orchestrate the logic of the application.
 */
public class CustomerFilterController {
    private static final double MAX_DISTANCE = 100;

    protected final CustomerReadService customerReadService;
    protected final DistanceFilterService distanceFilterService;
    protected final CustomerValidationService customerValidationService;
    protected final ErrorService errorService;
    protected final OutputService outputService;

    /**
     * Set up dependencies.
     *
     * @param customerReadService       Service to read the customer data from file.
     * @param distanceFilterService     Service to filter customers based on their distance to the office.
     * @param customerValidationService Service to validate customer data that has been read from a file.
     * @param errorService              Service to handle errors in the application.
     * @param outputService             Service to output data from the application.
     */
    public CustomerFilterController(CustomerReadService customerReadService,
                                    DistanceFilterService distanceFilterService,
                                    CustomerValidationService customerValidationService,
                                    ErrorService errorService,
                                    OutputService outputService) {
        this.distanceFilterService = distanceFilterService;
        this.customerReadService = customerReadService;
        this.customerValidationService = customerValidationService;
        this.errorService = errorService;
        this.outputService = outputService;
    }

    /**
     * Process the customer data file and output the result
     *
     * @param inputFileName Customer data input file.
     */
    public void processCustomerDataFile(String inputFileName) {
        processCustomerDataFile(inputFileName, null);
    }

    /**
     * Process the customer data file and output the result to the file as well as stdout.
     *
     * @param inputFileName  Input data file.
     * @param outputFileName Output file name.
     */
    public void processCustomerDataFile(String inputFileName, String outputFileName) {
        try {
            List<Customer> inputCustomerList = customerReadService.getCustomersFromFile(inputFileName);
            List<Customer> filteredCustomers = distanceFilterService.filterCustomersByDistance(inputCustomerList, MAX_DISTANCE);
            List<Customer> sortedCustomerList = filteredCustomers.stream().sorted(Customer.getComparator()).collect(Collectors.toList());
            outputService.outputCustomers(outputFileName, sortedCustomerList);
        } catch (InputException e) {
            //Do nothing - errors already output.
        } catch (Exception e) {
            //Any unexpected errors, print the stack trace.
            e.printStackTrace();
        }
    }
}
