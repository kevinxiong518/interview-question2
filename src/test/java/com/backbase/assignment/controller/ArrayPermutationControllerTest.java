package com.backbase.assignment.controller;

import com.backbase.assignment.exception.ArrayNotFoundException;
import com.backbase.assignment.exception.InvalidNumbersException;
import com.backbase.assignment.exception.MissingNumbersException;
import com.backbase.assignment.util.NumbersUtil;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
 * Scenario 3:
 * Store multiple arrays - storeArray("4,8,2"), storeArray("1,6,3,7"), storeArray("4,8,2")
 * Expected: same array is only stored once
 * In memory:
 * 1 -> [8, 4, 6]
 * 2 -> [1, 3, 11, 4]
 * 3 -> [7, 13, 2, 1]
 * 4 -> [9, 2, 7, 2]
 * 5 -> [3, 5, 6, 8, 10]
 * 6 -> [4, 8, 2]
 * 7 -> [1, 6, 3, 7]
 * <p>
 * Scenario 4:
 * Get permutation of the array with id = 10
 * Expected: throws ArrayNotFoundException
 * <p>
 * Scenario 5:
 * Get permutation of the array with id = 7
 * Expected: returns a permutation of [1, 6, 3, 7]
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
    @Order(5)
    public void testStoreMultipleArrays() {
        arrayPermutationController.storeArray("4,8,2");
        arrayPermutationController.storeArray("1,6,3,7");
        arrayPermutationController.storeArray("4,8,2");
        assertEquals(7, idToList.size());
        assertNotNull(idToList.get(6L));
        assertEquals("[4, 8, 2]", idToList.get(6L).toString());
        assertNotNull(idToList.get(7L));
        assertEquals("[1, 6, 3, 7]", idToList.get(7L).toString());
    }

    @Test
    public void testGetPermutationWithInvalidId() {
        assertThrows(ArrayNotFoundException.class, () -> arrayPermutationController.getPermutationById(10L));
    }

    @Test
    @Order(6)
    public void testGetPermutationById() {
        long id = 7;
        List<Integer> l1 = idToList.get(id);
        String permutation = arrayPermutationController.getPermutationById(id);
        List<Integer> l2 = NumbersUtil.convertToList(permutation.substring(1, permutation.length() - 1)).getNumList();
        assertTrue(NumbersUtil.isPermutation(l1, l2));
    }
}