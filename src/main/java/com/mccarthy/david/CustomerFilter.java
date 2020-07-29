package com.mccarthy.david;

import com.mccarthy.david.controller.CustomerFilterController;
import com.mccarthy.david.io.FileHandler;

/**
 * Driver class for the customer filter application.
 **/
public class CustomerFilter {
    /**
     * Entry point to the application.
     * Do some basic validation and pass off to the controller.
     *
     * @param args List of inputs to the application - customer data file name, output file name.
     */
    public static void main(String[] args) {
        CustomerFilterController controller = new CustomerFilterController(new FileHandler());
        System.out.println(args[0]);
        controller.processCustomerDataFile(args[0], args[1]);
    }
}
