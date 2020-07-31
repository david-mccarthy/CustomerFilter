package com.mccarthy.david.io;

import com.mccarthy.david.model.Customer;
import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test the output service.
 */
public class OutputServiceTest {
    private static final String OUTPUT_FILE = "src/test/resources/outputServiceTestOutput.txt";
    private OutputService outputService;

    public OutputServiceTest() {
        outputService = new OutputService();
    }

    @Test
    public void testCallingOutputServiceHasNoErrors() {
        List<Customer> customerList = new ArrayList<>();
        customerList.add(new Customer());
        try {
            new OutputService().outputCustomers(null, customerList);
        } catch (Exception e) {
            fail("Unexpected exception in calling output service.");
        }
    }

    @Test
    public void testOutputWithOutputFile() {
        List<Customer> customerList = new ArrayList<>();
        Customer c1 = new Customer();
        c1.setUserId(1);
        c1.setName("Name");
        c1.setLatitude(10d);
        c1.setLongitude(190d);
        customerList.add(c1);
        try {
            outputService.outputCustomers(OUTPUT_FILE, customerList);
            Path outputFile = Paths.get(OUTPUT_FILE);
            List<String> outputContents = Files.readAllLines(outputFile);
            assertEquals("Expect one entry in the output file.", 1, outputContents.size());
        } catch (Exception e) {
            fail("Unexpected exception in calling output service.");
        }
    }

    @After
    public void teardown() {
        //Remove test files.
        File file = new File(OUTPUT_FILE);
        file.delete();
    }
}