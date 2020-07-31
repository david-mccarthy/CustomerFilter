package com.mccarthy.david;

import com.mccarthy.david.controller.CustomerFilterController;
import com.mccarthy.david.error.ErrorService;
import com.mccarthy.david.io.CustomerReadService;
import com.mccarthy.david.io.OutputService;
import com.mccarthy.david.services.DistanceFilterService;
import com.mccarthy.david.services.DistanceService;
import com.mccarthy.david.validation.CustomerValidationService;
import org.omg.SendingContext.RunTime;

/**
 * Main class for the customer filter application.
 **/
public class Main {

    /**
     * Entry point to the application.
     * Do some basic validation and pass off to the controller.
     *
     * @param args List of inputs to the application. Expects single item - customer data file name.
     */
    public static void main(String[] args) {
        CustomerValidationService customerValidationService = new CustomerValidationService();
        ErrorService errorService = new ErrorService();
        CustomerReadService customerReadService = new CustomerReadService(customerValidationService, errorService);
        DistanceService distanceService = new DistanceService();
        DistanceFilterService distanceFilterService = new DistanceFilterService(distanceService);
        OutputService outputService = new OutputService();

        CustomerFilterController controller = new CustomerFilterController(customerReadService, distanceFilterService,
                customerValidationService, errorService, outputService);

        if (args.length == 1) {
            controller.processCustomerDataFile(args[0]);
        } else if (args.length == 2) {
            controller.processCustomerDataFile(args[0], args[1]);
        } else {
            System.err.println("Incorrect number of params provided");
            System.err.println("Usage: java -jar <app.jar> <customer data source file path>");
            throw new RuntimeException("Incorrect input to the application");
        }

    }
}
