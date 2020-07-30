package com.mccarthy.david.integration;

import com.mccarthy.david.Main;
import org.junit.Test;

public class CustomerFilterIntegrationTest {
    public static final String TEST_FILE = "src/test/resources/customers.txt";
    public static final String OUTPUT_FILE = "src/test/resources/output.txt";

    @Test
    public void testSuccess() {
        Main.main(new String[]{TEST_FILE, OUTPUT_FILE});

    }

    @Test
    public void testFailureCustomerDataFileNotFound() {
        Main.main(new String[]{"NonExistantInputFile", OUTPUT_FILE});
    }
}

