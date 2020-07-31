package com.mccarthy.david.validation;

import com.mccarthy.david.error.InputException;
import com.mccarthy.david.model.Customer;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mccarthy.david.validation.CustomerValidationService.*;
import static org.junit.Assert.*;

/**
 * Test the customer validation service.
 */
public class CustomerValidationServiceTest {
    private CustomerValidationService customerValidationService;
    private Map<Customer, List<String>> errors;
    private Customer testCustomer;

    @Before
    public void initialise() {
        customerValidationService = new CustomerValidationService();
        errors = new HashMap<>();
        testCustomer = new Customer();
    }

    @Test
    public void testValidateCustomerNoId() {
        testCustomer.setName("Name");
        testCustomer.setLatitude(100D);
        testCustomer.setLongitude(100D);
        customerValidationService.validateCustomer(testCustomer, errors);
        assertEquals(1, errors.size());
        List<String> errorStrings = errors.get(testCustomer);
        assertEquals(1, errorStrings.size());
        assertEquals(ID_IS_MISSING, errorStrings.get(0));
    }

    @Test
    public void testValidateCustomerNoName() {
        testCustomer.setUserId(1);
        testCustomer.setLatitude(100D);
        testCustomer.setLongitude(100D);
        customerValidationService.validateCustomer(testCustomer, errors);
        assertEquals(1, errors.size());
        List<String> errorStrings = errors.get(testCustomer);
        assertEquals(1, errorStrings.size());
        assertEquals(CUSTOMER_NAME_IS_MISSING, errorStrings.get(0));
    }

    @Test
    public void testValidateCustomerNoLatitude() {
        testCustomer.setUserId(1);
        testCustomer.setName("Name");
        testCustomer.setLongitude(100D);
        customerValidationService.validateCustomer(testCustomer, errors);
        assertEquals(1, errors.size());
        List<String> errorStrings = errors.get(testCustomer);
        assertEquals(1, errorStrings.size());
        assertEquals(LATITUDE_IS_MISSING, errorStrings.get(0));
    }

    @Test
    public void testValidateCustomerNoLongitude() {
        testCustomer.setUserId(1);
        testCustomer.setName("Name");
        testCustomer.setLatitude(100D);
        customerValidationService.validateCustomer(testCustomer, errors);
        assertEquals(1, errors.size());
        List<String> errorStrings = errors.get(testCustomer);
        assertEquals(1, errorStrings.size());
        assertEquals(LONGITUDE_IS_MISSING, errorStrings.get(0));
    }

    @Test
    public void testValidateCustomerMultipleIssues() {
        testCustomer.setName("Name");
        testCustomer.setLongitude(100D);
        customerValidationService.validateCustomer(testCustomer, errors);
        assertEquals(1, errors.size());
        List<String> errorStrings = errors.get(testCustomer);
        assertEquals(2, errorStrings.size());

        assertEquals(ID_IS_MISSING, errorStrings.get(0));
        assertEquals(LATITUDE_IS_MISSING, errorStrings.get(1));
    }

    @Test
    public void testValidateListOfCustomersNoIssues() {
        List<Customer> customers = new ArrayList<>();
        Customer c1 = new Customer();
        c1.setUserId(1);
        c1.setName("TestName");
        c1.setLatitude(5d);
        c1.setLongitude(10d);
        customers.add(c1);
        boolean inputExceptionThrown = false;
        Map<Customer, List<String>> errors = null;
        try {
            customerValidationService.validateCustomers(customers);
        } catch (InputException e) {
            inputExceptionThrown = true;
            errors = e.getCustomerDataErrors();
        }

        assertFalse("No exception should be thrown.", inputExceptionThrown);
        assertNull("There should be no errors.", errors);
    }

    @Test
    public void testValidateListOfCustomersWithValidationIssues() {
        List<Customer> customers = new ArrayList<>();
        Customer c1 = new Customer();
        c1.setUserId(1);
        c1.setLatitude(5d);
        c1.setLongitude(10d);
        customers.add(c1);
        boolean inputExceptionThrown = false;
        Map<Customer, List<String>> errors = null;
        try {
            customerValidationService.validateCustomers(customers);
        } catch (InputException e) {
            inputExceptionThrown = true;
            errors = e.getCustomerDataErrors();
        }

        assertTrue("Exception should be thrown.", inputExceptionThrown);
        assertEquals("There should be one customer error entry returned.", 1, errors.size());
        List<String> c1CustomerErrors = errors.get(c1);
        assertEquals("There should be one item in the error list.", 1, c1CustomerErrors.size());
        assertEquals(CUSTOMER_NAME_IS_MISSING, c1CustomerErrors.get(0));
    }
}