package com.mccarthy.david.controller;

import com.mccarthy.david.io.FileHandler;
import com.mccarthy.david.model.Customer;
import com.mccarthy.david.services.DistanceFilterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerFilterControllerTest {
    private static final String TEST_FILE = "TestFileName";
    private static final String OUTPUT_FILE = "TestOutputName";

    @Mock
    private FileHandler fileHandler;
    @Mock
    private DistanceFilterService distanceFilterService;

    private CustomerFilterController controller;

    @Before
    public void initialise() {
        controller = new CustomerFilterController(fileHandler, distanceFilterService);
    }

    @Test
    public void testExecutionFlow() throws Exception {
        ArrayList<Customer> customerList = generateTestCustomerList();
        when(fileHandler.getCustomersFromFile(anyString())).thenReturn(customerList);
        controller.processCustomerDataFile(TEST_FILE, OUTPUT_FILE);
//        verify(distanceFilterService.filterCustomersByDistance(eq(customerList), anyDouble()));
//        verify(fileHandler, times(1)).writeCustomersToFile(anyString(), anyList());
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