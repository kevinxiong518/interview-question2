package com.backbase.assignment.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.backbase.assignment.exception.InvalidNumbersException;
import com.backbase.assignment.model.NumberList;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Scenario 1:
 * Test NumbersUtil.convertToList with null or empty input
 * Expected: returns NumberList with an empty numList and empty stringValue
 * <p>
 * Scenario 2:
 * Test NumbersUtil.convertToList with numbers = "1,b,3"
 * Expected: throws InvalidNumbersException
 * <p>
 * Scenario 3:
 * Test NumbersUtil.convertToList with numbers = "1,4 5 6,3"
 * Expected: throws InvalidNumbersException
 * <p>
 * Scenario 4:
 * Test NumbersUtil.convertToList with numbers = "4,5,6,7"
 * Expected: returns NumberList with numList.toString() = [4, 5, 6, 7] and stringValue = "4,5,6,7"
 * <p>
 * Scenario 5:
 * Test NumbersUtil.convertToList with numbers = "  1, 2 ,   3 "
 * Expected: returns NumberList with numList.toString() = [1, 2, 3] and stringValue = "1,2,3,4"
 * <p>
 * Scenario 6:
 * Test NumbersUtil.isPermutation with null inputs
 * Expected: returns false
 * <p>
 * Scenario 7:
 * Test NumbersUtil.isPermutation with empty lists
 * Expected: returns true
 * <p>
 * Scenario 8:
 * Test NumbersUtil.isPermutation with lists of different sizes
 * Expected: return false;
 * <p>
 * Scenario 9:
 * Test NumbersUtil.isPermutation with lists of same size of different contents
 * Expected: return false;
 * <p>
 * Scenario 10:
 * Test NumbersUtil.isPermutation with permutations
 * Expected: return false;
 */
@SpringBootTest
public class NumbersUtilTest {

    @Test
    public void testConvertToListWithNullOrEmpty() {
        NumberList numberList = NumbersUtil.convertToList(null);
        assertNotNull(numberList);
        assertTrue(numberList.getNumList().isEmpty());
        assertTrue(numberList.getStringValue().isEmpty());
        numberList = NumbersUtil.convertToList("    ");
        assertNotNull(numberList);
        assertTrue(numberList.getNumList().isEmpty());
        assertTrue(numberList.getStringValue().isEmpty());
    }

    @Test
    public void testConvertToListWithNonInt() {
        assertThrows(InvalidNumbersException.class, () -> NumbersUtil.convertToList("1,b,3"));
    }

    @Test
    public void testConvertToListWithWrongFormat() {
        assertThrows(InvalidNumbersException.class, () -> NumbersUtil.convertToList("1,4 5 6,3"));
    }

    @Test
    public void testConvertToList() {
        NumberList numberList = NumbersUtil.convertToList("4,5,6,7");
        assertNotNull(numberList);
        assertEquals("[4, 5, 6, 7]", numberList.getNumList().toString());
        assertEquals("4,5,6,7", numberList.getStringValue());
    }

    @Test
    public void testConvertToListWithEmptySpaces() {
        NumberList numberList = NumbersUtil.convertToList("  1, 2 ,   3 ");
        assertNotNull(numberList);
        assertEquals("[1, 2, 3]", numberList.getNumList().toString());
        assertEquals("1,2,3", numberList.getStringValue());
    }

    @Test
    public void testIsPermutationWithNull() {
        List<Integer> l1 = null;
        List<Integer> l2 = null;
        assertFalse(NumbersUtil.isPermutation(l1, l2));
    }

    @Test
    public void testIsPermutationWithEmptyLists() {
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        assertTrue(NumbersUtil.isPermutation(l1, l2));
    }

    @Test
    public void testIsPermutationWithDiffSizes() {
        List<Integer> l1 = Arrays.asList(1, 2);
        List<Integer> l2 = Arrays.asList(2, 1, 3);
        assertFalse(NumbersUtil.isPermutation(l1, l2));
    }

    @Test
    public void testIsPermutationWithDiffContents() {
        List<Integer> l1 = Arrays.asList(4, 2, 7);
        List<Integer> l2 = Arrays.asList(7, 11, 3);
        assertFalse(NumbersUtil.isPermutation(l1, l2));
    }

    @Test
    public void testIsPermutationWithPermutations() {
        List<Integer> l1 = Arrays.asList(23, 5, 7, 9);
        List<Integer> l2 = Arrays.asList(9, 7, 5, 23);
        assertTrue(NumbersUtil.isPermutation(l1, l2));
    }

}
