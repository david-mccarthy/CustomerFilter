package com.mccarthy.david;

import com.mccarthy.david.controller.CustomerFilterController;
import com.mccarthy.david.io.FileHandler;
import com.mccarthy.david.services.DistanceFilterService;
import com.mccarthy.david.services.DistanceService;

/**
 * Driver class for the customer filter application.
 **/
public class Main {
    /**
     * Entry point to the application.
     * Do some basic validation and pass off to the controller.
     *
     * @param args List of inputs to the application - customer data file name, output file name.
     */
    public static void main(String[] args) {
        CustomerFilterController controller = new CustomerFilterController(new FileHandler(), new DistanceFilterService(new DistanceService()));

        if (args.length != 2) {
            System.err.println("Incorrect number of params provided");
            System.err.println("Usage: java -jar <app.jar> <customer data source file path> <output file path>");
        }
        controller.processCustomerDataFile(args[0], args[1]);
    }
}
