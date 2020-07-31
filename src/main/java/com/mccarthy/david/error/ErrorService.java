package com.mccarthy.david.error;

import com.mccarthy.david.model.Customer;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Service to handle errors that occur in the application.
 */
public class ErrorService {
    Logger logger = Logger.getLogger("CustomerFilter");

    /**
     * Log the input errors.
     *
     * @param e Input exception, containing the errors.
     */
    public void logInputErrors(InputException e) {
        StringBuilder stringBuilder = new StringBuilder();

        List<String> inputErrors = e.getInputErrors();
        if (inputErrors != null && !inputErrors.isEmpty()) {
            stringBuilder.append("Errors found with these records while reading from customer file:\n");
            for (String inputError : inputErrors) {
                stringBuilder.append("  ");
                stringBuilder.append(inputError);
                stringBuilder.append("\n");
            }
        }
        Map<Customer, List<String>> customerDataErrors = e.getCustomerDataErrors();

        if (customerDataErrors != null && !customerDataErrors.isEmpty()) {
            stringBuilder.append("\n");
            stringBuilder.append("Errors found while validating customer data read from file:\n");
            for (Map.Entry<Customer, List<String>> customerDataError : customerDataErrors.entrySet()) {
                stringBuilder.append("Validation errors for customer: ");
                stringBuilder.append(customerDataError.getKey().toString());
                stringBuilder.append("\n");
                for (String error : customerDataError.getValue()) {
                    stringBuilder.append("  ");
                    stringBuilder.append(error);
                    stringBuilder.append("\n");
                }
            }
        }

        if (stringBuilder.length() != 0) {
            logger.log(Level.SEVERE, stringBuilder.toString());
        }
    }
}
