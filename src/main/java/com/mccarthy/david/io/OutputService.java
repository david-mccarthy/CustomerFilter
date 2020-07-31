package com.mccarthy.david.io;

import com.mccarthy.david.model.Customer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Service for outputting data from the application.
 */
public class OutputService {
    private final Logger logger = Logger.getLogger("OutputService");

    /**
     * Output the customer data.
     * Currently to stdout, could be overridden to output to somewhere else.
     *
     * @param customerList List of customers to output.
     */
    public void outputCustomers(String outputFileName, List<Customer> customerList) {
        for (Customer customer : customerList) {
            System.out.println(customer.getNameAndId());
        }
        if (outputFileName != null && !"".equals(outputFileName)) {
            writeCustomersToFile(outputFileName, customerList);
        }
    }

    /**
     * Write the list of customers to the output file.
     *
     * @param outputFileName Name of the output file.
     * @param customers      List of customers to write to file.
     */
    public void writeCustomersToFile(String outputFileName, List<Customer> customers) {
        FileWriter outputFileWriter = null;
        try {
            outputFileWriter = new FileWriter(outputFileName);
            for (Customer customer : customers) {
                outputFileWriter.write(customer.getNameAndId() + "\n");
            }
        } catch (IOException ioe) {
            //We don't want failure to write to the output file to stop execution. Log and move on.
            logger.log(Level.INFO, "Exception when trying to open output file.");
        } finally {
            try {
                outputFileWriter.close();
            } catch (IOException | NullPointerException e) {
                logger.log(Level.INFO, "IOException when trying to close FileWriter. Likely the writer errored in creation.");
            }
        }
    }
}
