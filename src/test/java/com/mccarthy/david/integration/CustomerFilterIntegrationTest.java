package com.mccarthy.david.integration;

import com.mccarthy.david.Main;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Run an integration test processing the input file and checking the output.
 */
public class CustomerFilterIntegrationTest {

    public static final String TEST_FILE = "src/test/resources/customers.txt";
    public static final String OUTPUT_FILE = "src/test/resources/testOutput.txt";

    @Test
    public void testSuccess() throws Exception {
        Main.main(new String[]{TEST_FILE, OUTPUT_FILE});
        Path output = Paths.get(OUTPUT_FILE);
        List<String> outputData = Files.readAllLines(output);
        assertEquals("Expected 16 results to be output.", 16, outputData.size());
    }

    @After
    public void teardown() {
        //Remove test files.
        File file = new File(OUTPUT_FILE);
        file.delete();
    }
}

