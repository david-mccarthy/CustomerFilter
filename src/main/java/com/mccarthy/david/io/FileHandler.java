package com.mccarthy.david.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mccarthy.david.model.Customer;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to handle dealing with the file input and output.
 */
public class FileHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

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
            System.out.println("IOException when trying to open file: " + customerListFileName);
            e.printStackTrace();
            throw e;
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
                outputFileWriter.write(customer.toString() + "\n");
            }
        } catch (IOException ioe) {
            System.err.println("Exception when trying to open output file.");
        } finally {
            try {
                outputFileWriter.close();
            } catch (IOException|NullPointerException e) {
                System.out.println("IOException when trying to close FileWriter. Likely the writer errored in creation.");
            }
        }
    }

    /**
     * Map the list of customer details as a json string to a list of customers.
     *
     * @param customerListAsString List of customer details as json string.
     * @return List of customers.
     */
    protected List<Customer> mapToListOfCustomers(List<String> customerListAsString) {
        List<Customer> customers = new ArrayList<>();

        for (String customerString : customerListAsString) {
            customers.add(mapCustomer(customerString));
        }

        return customers;
    }

    /**
     * Map a json string customer data entry to a Custome object.
     *
     * @param customerData Customer data as a json formatted string.
     * @return Customer object.
     */
    protected Customer mapCustomer(String customerData) {
        Customer customer = null;
        try {
            customer = objectMapper.readValue(customerData, Customer.class);
        } catch (IOException ioe) {
            System.err.println("Exception parsing customer input data.");
            System.exit(1);
        }
        return customer;
    }
}
