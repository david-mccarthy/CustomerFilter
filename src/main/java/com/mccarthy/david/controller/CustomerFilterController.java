package com.mccarthy.david.controller;

import com.mccarthy.david.io.FileHandler;
import com.mccarthy.david.model.Customer;
import com.mccarthy.david.services.DistanceFilterService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller class to orchestrate the logic of the application.
 */
public class CustomerFilterController {
    private static final double MAX_DISTANCE = 100;

    protected final FileHandler fileHandler;
    protected final DistanceFilterService distanceFilterService;

    public CustomerFilterController(FileHandler fileHandler, DistanceFilterService distanceFilterService) {
        this.distanceFilterService = distanceFilterService;
        this.fileHandler = fileHandler;
    }

    public void processCustomerDataFile(String inputFileName, String outputFileName) {
        try {
            List<Customer> initialCustomerList = fileHandler.getCustomersFromFile(inputFileName);
            List<Customer> filteredCustomers = distanceFilterService.filterCustomersByDistance(initialCustomerList, MAX_DISTANCE);
            List<Customer> sortedCustomerList = filteredCustomers.stream().sorted(Customer.getComparator()).collect(Collectors.toList());
            //Output file to STDOUT.
            fileHandler.writeCustomersToFile(outputFileName, sortedCustomerList);
            for(Customer c : sortedCustomerList){
                System.out.println(c);
            }
        } catch (Exception e) {
            System.out.println("Exception thrown in application and caught gracefully in the end");
        }
    }
}
