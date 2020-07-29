package com.mccarthy.david.controller;

import com.mccarthy.david.model.Customer;
import com.mccarthy.david.io.FileHandler;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller class to orchestrate the logic of the application.
 */
public class CustomerFilterController {
    protected FileHandler fileHandler;

    public CustomerFilterController(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    public void processCustomerDataFile(String inputFileName, String outputFileName) {
        try {
            List<Customer> customersFromFile = fileHandler.getCustomersFromFile(inputFileName);

            //Filter customers.

            fileHandler.writeCustomersToFile(outputFileName, customersFromFile.stream().sorted(new Comparator<Customer>() {
                @Override
                public int compare(Customer o1, Customer o2) {
                    if (o1.getUserId() < o2.getUserId()) {
                        return -1;
                    } else if (o1.getUserId() == o2.getUserId()) {
                        return 0;
                    }
                    return 1;
                }
            }).collect(Collectors.toList()));
        } catch (Exception e) {
            System.out.println("Exception thrown in application and caught gracefully in the end");
        }
    }
}
