package com.mccarthy.david.controller;

import com.mccarthy.david.io.FileHandler;
import com.mccarthy.david.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerFilterControllerTest {
    private final String TEST_FILE = "TestFileName";
    private final String OUTPUT_FILE = "TestOutputName";

    @Mock
    private FileHandler fileHandler;

    private CustomerFilterController controller;

    @Before
    public void initialise() {
        controller = new CustomerFilterController(fileHandler);
    }

    @Test
    public void testExecutionFlow() throws Exception {
        ArrayList<Customer> customerList = generateTestCustomerList();
        when(fileHandler.getCustomersFromFile(anyString())).thenReturn(customerList);
        controller.processCustomerDataFile(TEST_FILE, OUTPUT_FILE);
        verify(fileHandler, times(1)).writeCustomersToFile(anyString(), eq(customerList));
    }

    private ArrayList<Customer> generateTestCustomerList() {
        ArrayList<Customer> customers = new ArrayList<>();
        Customer customer1 = new Customer();
        customer1.setUserId(1);
        customers.add(customer1);
        Customer customer2 = new Customer();
        customer2.setUserId(2);
        customers.add(customer2);

        return customers;
    }
}