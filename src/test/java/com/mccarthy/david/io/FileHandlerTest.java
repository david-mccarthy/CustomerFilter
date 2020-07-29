package com.mccarthy.david.io;

import com.mccarthy.david.model.Customer;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FileHandlerTest {
    public static final String CUSTOMER_DATA_FORMAT = "{\"latitude\": \"%s\", \"user_id\": %s, \"name\": \"%s\", \"longitude\": \"%s\"}";
    public static final String TEST_FILE = "src/test/resources/customers.txt";
    private FileHandler fileUtils;
    private List<String> customerStringList;

    @Before
    public void initialise() {
        fileUtils = new FileHandler();
        customerStringList = new ArrayList<>();
    }

    /**
     * Test that we can read a file and process it into a list of customers.
     */
    @Test
    public void testReadFileAndProcess() throws Exception{
        List<Customer> customersFromFile = fileUtils.getCustomersFromFile(TEST_FILE);
        assertNotNull(customersFromFile);
        assertEquals("Incorrect number of customers created from file.",32,customersFromFile.size());
    }

    /**
     * This test builds on {@link #testCreateCustomerFromCustomerData()}.
     * Assuming the dependency test passes, we know we can create a customer with correct data.
     * This test asserts that a list of customer data as string can be porsed into a list of Customer objects.
     */
    @Test
    public void testMapListOfCustomerDataToCustomerModel() {
        customerStringList.add(createTestCustomer("1","TestCustomer1","54.12345","-2.12312"));
        customerStringList.add(createTestCustomer("2","TestCustomer2","45.22334","-5.22223"));
        customerStringList.add(createTestCustomer("3","TestCustomer3","23.40404","-10.22022"));
        List<Customer> customers = fileUtils.mapToListOfCustomers(customerStringList);
        assertEquals("Number of customers should be 4", 4, customers.size());
        for(Customer customer: customers){
            //No need to test primitive int userId - cannot be null.
            assertNotNull("Customer should not be null.",customer);
            assertNotNull("Customer name is not null.", customer.getName());
            assertNotNull("Customer latitude should not be null.",customer.getLatitude());
            assertNotNull("Customers longitude should not be null.", customer.getLongitude());
        }
    }

    @Test
    public void testCreateCustomerFromCustomerData() {
        String userId = "100";
        String customerName = "TestCustomerName";
        String latitude = "52.833502";
        String longitude = "-8.522366";
        Customer customer = fileUtils.mapCustomer(createTestCustomer(userId, customerName, latitude, longitude));

        assertNotNull("Customer should not be null", customer);
        assertEquals("User id does not match", Integer.parseInt(userId), customer.getUserId());
        assertEquals("Customer name does not match", customerName, customer.getName());
        assertEquals("Latitude does not match", new BigDecimal(latitude), customer.getLatitude());
        assertEquals("Longitude does not match", new BigDecimal(longitude), customer.getLongitude());
    }

    //Test file does not exist

    //Test file exists, but is empty

    //Test file exists, has bad entries

    private String createTestCustomer(String userId, String customerName, String latitude, String longitude) {
        return String.format(CUSTOMER_DATA_FORMAT, latitude, userId, customerName, longitude);
    }
}