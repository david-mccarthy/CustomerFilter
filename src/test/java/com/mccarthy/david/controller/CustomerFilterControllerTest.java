package com.mccarthy.david.controller;

import com.mccarthy.david.error.ErrorService;
import com.mccarthy.david.io.CustomerReadService;
import com.mccarthy.david.io.OutputService;
import com.mccarthy.david.services.DistanceFilterService;
import com.mccarthy.david.validation.CustomerValidationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Test the customer filter controller.
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerFilterControllerTest {
    private static final String TEST_FILE = "TestFileName";

    @Mock
    private CustomerReadService customerReadService;
    @Mock
    private DistanceFilterService distanceFilterService;
    @Mock
    private CustomerValidationService customerValidationService;
    @Mock
    private ErrorService errorService;
    @Mock
    private OutputService outputService;

    private CustomerFilterController controller;

    @Before
    public void initialise() {
        controller = new CustomerFilterController(customerReadService, distanceFilterService, customerValidationService,
                errorService, outputService);
    }

    /**
     * Controller test needs only verify the orchestration of the logic, that the correct services are called with the corect input.
     */
    @Test
    public void testExecutionFlow() throws Exception {
        when(customerReadService.getCustomersFromFile(anyString())).thenReturn(new ArrayList<>());
        controller.processCustomerDataFile(TEST_FILE);
        verify(customerReadService, times(1)).getCustomersFromFile(anyString());
        verify(distanceFilterService, times(1)).filterCustomersByDistance(anyList(), anyDouble());
        verify(outputService, times(1)).outputCustomers(isNull(), anyList());
    }
}