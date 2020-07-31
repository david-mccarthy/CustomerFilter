package com.mccarthy.david.error;

import com.mccarthy.david.model.Customer;

import java.util.List;
import java.util.Map;

/**
 * Exception thrown when there are issues with the input.
 */
public class InputException extends Exception {
    private Map<Customer, List<String>> customerDataErrors;
    private List<String> inputErrors;

    /**
     * Constructor taking in a map of customer errors.
     *
     * @param customerDataErrors Customer errors as map of Customer-> list of errors.
     */
    public InputException(Map<Customer, List<String>> customerDataErrors) {
        super();
        this.customerDataErrors = customerDataErrors;
    }

    /**
     * Construtor taking a list of input errors.
     *
     * @param inputErrors List of input errors.
     */
    public InputException(List<String> inputErrors) {
        super();
        this.inputErrors = inputErrors;
    }

    /**
     * Get the map of customer errors.
     *
     * @return Map of errors in the form of customer -> list of customer errors.
     */
    public Map<Customer, List<String>> getCustomerDataErrors() {
        return this.customerDataErrors;
    }

    /**
     * Get the list of input errors, or issues when trying to read the input file.
     *
     * @return List of input errors.
     */
    public List<String> getInputErrors() {
        return this.inputErrors;
    }

    /**
     * Set the list of input errors for the exception.
     *
     * @param inputErrors List of input errors.
     */
    public void setInputErrors(List<String> inputErrors) {
        this.inputErrors = inputErrors;
    }
}
