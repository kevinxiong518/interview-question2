package com.backbase.assignment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.backbase.assignment.exception.ArrayNotFoundException;
import com.backbase.assignment.exception.InvalidNumbersException;
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

/**
 * Scenario 1:
 * Test storeArray with null or empty input
 * Expected: returns InvalidNumbersException
 * <p>
 * Scenario 2:
 * Test storeArray with invalid numbers
 * Expected: returns InvalidNumbersException
 * <p>
 * Scenario 3:
 * Store on single array into memory - storeArray("8,4,6")
 * Expected: has an array [8, 4, 6] with id = 1 in memory
 * In memory:
 * 1 -> [8, 4, 6]
 * <p>
 * Scenario 4:
 * Store the same array multiple times - call storeArray("1,3,11,4") 3 times
 * Expected: only store the array once
 * In memory:
 * 1 -> [8, 4, 6]
 * 2 -> [1, 3, 11, 4]
 * <p>
 * Scenario 5:
 * Store multiple different arrays - storeArray("7,13,2,1"); storeArray("9,2,7,2"); storeArray("3,5,6,8,10");
 * Expected: store 3 array in memory
 * In memory:
 * 1 -> [8, 4, 6]
 * 2 -> [1, 3, 11, 4]
 * 3 -> [7, 13, 2, 1]
 * 4 -> [9, 2, 7, 2]
 * 5 -> [3, 5, 6, 8, 10]
 * <p>
 * Scenario 6:
 * Get permutation of the array with id = 99
 * Expected: ArrayNotFoundException
 * <p>
 * Scenario 7:
 * Get permutation of the array with id = 3
 * Expected: returns a permutation of [7, 13, 2, 1]
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class ArrayPermutationServiceTest {

    @SpyBean
    private Map<Long, List<Integer>> idToList;

    @Autowired
    private ArrayPermutationService arrayPermutationService;

    @Test
    public void testStoreArrayWithNullOrEmptyNumbers() {
        assertThrows(InvalidNumbersException.class, () -> arrayPermutationService.storeArray(null));
        assertThrows(InvalidNumbersException.class, () -> arrayPermutationService.storeArray("  "));
    }

    @Test
    public void testStoreArrayWithInvalidNumbers() {
        assertThrows(InvalidNumbersException.class, () -> arrayPermutationService.storeArray("1,a,6"));
    }

    @Test
    @Order(1)
    public void testStoreSingleArray() {
        arrayPermutationService.storeArray("8,4,6");
        assertEquals(1, idToList.size());
        assertNotNull(idToList.get(1L));
        assertEquals("[8, 4, 6]", idToList.get(1L).toString());
    }

    @Test
    @Order(2)
    public void testStoreSameArrayMultipleTimes() {
        arrayPermutationService.storeArray("1,3,11,4");
        arrayPermutationService.storeArray("1,3,11,4");
        arrayPermutationService.storeArray("1,3,11,4");
        assertEquals(2, idToList.size());
        assertNotNull(idToList.get(2L));
        assertEquals("[1, 3, 11, 4]", idToList.get(2L).toString());
    }

    @Test
    @Order(3)
    public void testStoreMultipleArrays() {
        arrayPermutationService.storeArray("7,13,2,1");
        arrayPermutationService.storeArray("9,2,7,2");
        arrayPermutationService.storeArray("3,5,6,8,10");
        assertEquals(5, idToList.size());
        assertNotNull(idToList.get(3L));
        assertEquals("[7, 13, 2, 1]", idToList.get(3L).toString());
        assertNotNull(idToList.get(4L));
        assertEquals("[9, 2, 7, 2]", idToList.get(4L).toString());
        assertNotNull(idToList.get(5L));
        assertEquals("[3, 5, 6, 8, 10]", idToList.get(5L).toString());
    }

    @Test
    public void testGetPermutationWithInvalidId() {
        assertThrows(ArrayNotFoundException.class, () -> arrayPermutationService.getPermutationById(99L));
    }

    @Test
    @Order(4)
    public void testGetPermutationById() {
        long id = 3;
        List<Integer> l1 = idToList.get(id);
        String permutation = arrayPermutationService.getPermutationById(id);
        List<Integer> l2 = NumbersUtil.convertToList(permutation.substring(1, permutation.length() - 1)).getNumList();
        assertTrue(NumbersUtil.isPermutation(l1, l2));
    }

}