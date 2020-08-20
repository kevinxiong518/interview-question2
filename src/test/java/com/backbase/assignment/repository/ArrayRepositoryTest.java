package com.backbase.assignment.repository;

import com.backbase.assignment.entity.ArrayEntity;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Scenario 1:
 * Save an ArrayEntity into DB then retrieve it using its numbersString
 * Expected: retrieves the entity successfully
 * <p>
 * Scenario 2:
 * Try to retrieve an entity with a null or empty numbersString
 * Expected: empty result set
 * <p>
 * Scenario 3:
 * Try to retrieve an entity with a numbersString that does not exist
 * Expected: empty result set
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@Transactional
public class ArrayRepositoryTest {
    @Autowired
    private ArrayRepository arrayRepository;

    private String numString = "1,3,5,7";

    @Test
    public void testFindArrayByNumberString() {
        ArrayEntity arrayEntity = new ArrayEntity();
        arrayEntity.setNumbers(Arrays.asList(1, 3, 5, 7));
        arrayEntity.setNumbersString(numString);
        arrayRepository.save(arrayEntity);
        List<ArrayEntity> arrayEntityList = arrayRepository.findArraysByNumbersString(numString);
        assertNotNull(arrayEntityList);
        assertEquals(1, arrayEntityList.size());

        assertEquals("[1, 3, 5, 7]", arrayEntityList.get(0).getNumbers().toString());
    }

    @Test
    public void testFindArrayWithNullOrEmpty() {
        List<ArrayEntity> arrayEntityList = arrayRepository.findArraysByNumbersString(null);
        assertNotNull(arrayEntityList);
        assertTrue(arrayEntityList.isEmpty());
        arrayEntityList = arrayRepository.findArraysByNumbersString("");
        assertNotNull(arrayEntityList);
        assertTrue(arrayEntityList.isEmpty());
    }

    @Test
    public void testFindArrayWithRandomNumberString() {
        List<ArrayEntity> arrayEntityList = arrayRepository.findArraysByNumbersString("2,4,6");
        assertNotNull(arrayEntityList);
        assertTrue(arrayEntityList.isEmpty());
    }

}