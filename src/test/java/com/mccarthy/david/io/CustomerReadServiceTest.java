package com.mccarthy.david.io;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.mccarthy.david.error.ErrorService;
import com.mccarthy.david.model.Customer;
import com.mccarthy.david.utils.TestHelper;
import com.mccarthy.david.validation.CustomerValidationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test the customer read service.
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerReadServiceTest {
    private static final String TEST_FILE = "src/test/resources/customers.txt";
    private List<String> customerStringList;

    @Mock
    private CustomerValidationService customerValidationService;
    @Mock
    private ErrorService errorService;
    private CustomerReadService customerReadService;

    @Before
    public void initialise() {
        customerReadService = new CustomerReadService(customerValidationService, errorService);
        customerStringList = new ArrayList<>();
    }

    /**
     * Test that we can read a file and process it into a list of customers.
     */
    @Test
    public void testReadFileAndProcess() throws Exception {
        List<Customer> customersFromFile = customerReadService.getCustomersFromFile(TEST_FILE);
        assertNotNull(customersFromFile);
        assertEquals("Incorrect number of customers created from file.", 32, customersFromFile.size());
    }

    /**
     * This test builds on {@link #testCreateCustomerFromCustomerData()}.
     * Assuming that test passes, we know we can create a customer with correct data.
     * This test asserts that a list of customer data as string can be parsed into a list of Customer objects.
     */
    @Test
    public void testMapListOfCustomerDataToCustomerModel() throws Exception {
        customerStringList.add(TestHelper.createTestCustomerString("1", "TestCustomer1", 54.12345, -2.12312));
        customerStringList.add(TestHelper.createTestCustomerString("2", "TestCustomer2", 45.22334, -5.22223));
        customerStringList.add(TestHelper.createTestCustomerString("3", "TestCustomer3", 23.40404, -10.22022));
        List<Customer> customers = customerReadService.mapToListOfCustomers(customerStringList);
        assertEquals("Number of customers should be 3", 3, customers.size());
        for (Customer customer : customers) {
            //Verify that the customer is not null. Verify the fields are correctly mapped in another test.
            assertNotNull("Customer should not be null.", customer);
        }
    }

    @Test
    public void testCreateCustomerFromCustomerData() throws Exception {
        String userId = "100";
        String customerName = "TestCustomerName";
        double latitude = 52.833502;
        double longitude = -8.522366;
        Customer customer = customerReadService.mapCustomer(TestHelper.createTestCustomerString(userId, customerName, latitude, longitude));

        assertNotNull("Customer should not be null", customer);
        assertEquals("User id does not match", Integer.parseInt(userId), customer.getUserId(), 0);
        assertEquals("Customer name does not match", customerName, customer.getName());
        assertEquals("Latitude does not match", latitude, customer.getLatitude(), 0);
        assertEquals("Longitude does not match", longitude, customer.getLongitude(), 0);
    }

    @Test
    public void testCreateCustomerFromCustomerDataWithInvalidEntries() {
        String userId = "100";
        String customerName = "TestCustomerName";
        boolean jsonMappingExceptionThrown = false;
        String testCustomerString = TestHelper.createTestCustomerString(userId, customerName, "Hello", "");
        try {
            Customer customer = customerReadService.mapCustomer(testCustomerString);
        } catch (JsonMappingException e) {
            jsonMappingExceptionThrown = true;
        } catch (Exception e) {
            fail("Unexpected exception thrown for invalid customer data.");
        }
        assertTrue("Expect bad json data to throw a json mapping exception.", jsonMappingExceptionThrown);
    }

    @Test
    public void testInputFileDoesNotExist() {
        boolean ioExceptionThrown = false;
        try {
            customerReadService.getCustomersFromFile("NonExistantFileNam");
        } catch (IOException e) {
            ioExceptionThrown = true;
        } catch (Exception e) {
            fail("Unexpected exception for input file not existing.");
        }

        assertTrue("Expect an IO exception when the input file cannot be found.", ioExceptionThrown);
    }
}