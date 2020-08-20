package com.backbase.assignment.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.backbase.assignment.exception.ArrayNotFoundException;
import com.backbase.assignment.exception.InvalidNumbersException;
import com.backbase.assignment.exception.MissingNumbersException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.List;
import java.util.Map;

/**
 * Certain scenarios follow the test cases in ArrayPermutationServiceTest.
 * Scenario 1:
 * Test storeArray with null or empty input
 * Expected: throws MissingNumbersException
 * <p>
 * Scenario 2:
 * Test storeArray with invalid numbers
 * Expected: throws InvalidNumbersException
 * <p>
 * Scenario 4:
 * Get permutation of the array with id = 10
 * Expected: throws ArrayNotFoundException
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class ArrayPermutationControllerTest {

    @SpyBean
    private Map<Long, List<Integer>> idToList;

    @Autowired
    private ArrayPermutationController arrayPermutationController;

    @Test
    public void testStoreArrayWithNullOrEmpty() {
        assertThrows(MissingNumbersException.class, () -> arrayPermutationController.storeArray(null));
        assertThrows(MissingNumbersException.class, () -> arrayPermutationController.storeArray(""));
    }

    @Test
    public void testStoreArrayWithInvalidNumbers() {
        assertThrows(InvalidNumbersException.class, () -> arrayPermutationController.storeArray("5,u,9"));
    }

    @Test
    public void testGetPermutationWithInvalidId() {
        assertThrows(ArrayNotFoundException.class, () -> arrayPermutationController.getPermutationById(10L));
    }

}