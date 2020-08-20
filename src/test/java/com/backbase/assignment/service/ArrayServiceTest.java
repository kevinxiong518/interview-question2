package com.backbase.assignment.service;

import com.backbase.assignment.entity.ArrayEntity;
import com.backbase.assignment.exception.ArrayNotFoundException;
import com.backbase.assignment.repository.ArrayRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Mocked up the persistence layer for testing
 * <p>
 * Scenario 1:
 * Store a new array into the database
 * Expected: an ArrayEntity is returned with a new ID
 * <p>
 * Scenario 2:
 * Store an array that exists already
 * Expected: the same ArrayEntity is return everytime
 * <p>
 * Scenario 3:
 * Search with an ID that does not exist
 * Expected: throws ArrayNotFoundException
 * <p>
 * Scenario 4:
 * Find array with a valid ID
 * Expected: the array is returned
 */
@SpringBootTest
public class ArrayServiceTest {

    @MockBean
    private ArrayRepository arrayRepository;

    @Autowired
    private ArrayService arrayService;

    private ArrayEntity oldEntity;

    private ArrayEntity newEntity;

    @BeforeEach
    public void init() {

        oldEntity = new ArrayEntity(1L, Arrays.asList(8, 4, 9, 5), "8,4,9,5");
        Mockito.when(arrayRepository.findById(1L)).thenReturn(Optional.of(oldEntity));

        List<ArrayEntity> arrayEntityList = new ArrayList<>();
        arrayEntityList.add(oldEntity);
        Mockito.when(arrayRepository.findArraysByNumbersString("8,4,9,5"))
                .thenReturn(arrayEntityList);

        newEntity = new ArrayEntity();
        newEntity.setNumbersString("3,7,11,4");
        newEntity.setNumbers(Arrays.asList(3, 7, 11, 4));

        Mockito.when(arrayRepository.findArraysByNumbersString("3,7,11,4"))
                .thenReturn(new ArrayList<>());
        Mockito.when(arrayRepository.save(newEntity))
                .thenAnswer(ArrayEntity -> new ArrayEntity(2L, Arrays.asList(3, 7, 11, 4), "3,7,11,4"));

        Mockito.when(arrayRepository.findById(5L)).thenReturn(Optional.empty());
    }

    @Test
    public void saveNewArray() {
        assertNull(newEntity.getId());
        ArrayEntity arrayEntity = arrayService.saveArray(newEntity);
        assertNotNull(arrayEntity.getId());
    }

    @Test
    public void sameExistingArray() {
        ArrayEntity ae1 = arrayService.saveArray(oldEntity);
        ArrayEntity ae2 = arrayService.saveArray(oldEntity);
        assertNotNull(ae1);
        assertNotNull(ae2);
        assertEquals(ae1.getId(), ae2.getId());
    }

    @Test
    public void findArrayWithInvalidId() {
        assertThrows(ArrayNotFoundException.class, () -> arrayService.findArrayById(5L));
    }

    @Test
    public void findArrayById() {
        ArrayEntity arrayEntity = arrayService.findArrayById(1L);
        assertNotNull(arrayEntity);
    }
}