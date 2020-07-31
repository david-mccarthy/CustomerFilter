package com.mccarthy.david.error;

import com.mccarthy.david.model.Customer;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;

/**
 * Test the error service.
 */
public class ErrorServiceTest {
    private ErrorService errorService;

    @Before
    public void initialise() {
        this.errorService = new ErrorService();
    }

    @Test
    public void testCallingErrorServiceGivesNoErrors() {
        Map<Customer, List<String>> errors = new HashMap<>();
        InputException inputException = new InputException(errors);
        boolean errorsThrown = false;
        try {
            errorService.logInputErrors(inputException);
        } catch (Exception e) {
            errorsThrown = true;
        }

        assertFalse("No exceptions should be thrown in the application.", errorsThrown);
    }
}