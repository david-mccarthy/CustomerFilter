package com.mccarthy.david.services;

import com.mccarthy.david.model.Customer;
import com.mccarthy.david.utils.TestHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

/**
 * Test the distance filter service.
 */
@RunWith(MockitoJUnitRunner.class)
public class DistanceFilterServiceTest {
    @Mock
    private DistanceService distanceService;

    private DistanceFilterService distanceFilterService;

    @Before
    public void setup() {
        distanceFilterService = new DistanceFilterService(distanceService);
    }

    @Test
    public void testFilterCustomerList() {
        List<Customer> customers = new ArrayList<>();
        customers.add(TestHelper.createTestCustomer(1, "Test Customer 1", 54.11111, -1.2222));
        customers.add(TestHelper.createTestCustomer(2, "Test Customer 2", 54.11112, -1.2223));
        customers.add(TestHelper.createTestCustomer(3, "Test Customer 3", 54.11113, -1.2224));

        when(distanceService.calculateDistance(anyDouble(), anyDouble(), anyDouble(), anyDouble())).thenReturn(99D).thenReturn(100D).thenReturn(101D);
        List<Customer> filteredCustomers = distanceFilterService.filterCustomersByDistance(customers, 100);
        assertEquals("Size of filtered list is incorrect.", 1, filteredCustomers.size());
        assertEquals("Expecting to get user with id = 1 returned.", 1, filteredCustomers.get(0).getUserId(), 0);
    }
}