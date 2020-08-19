package com.backbase.assignment;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.backbase.assignment.controller.ArrayPermutationController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AssignmentApplicationTest {

    @Autowired
    private ArrayPermutationController arrayPermutationController;

    @Test
    public void contextLoads() {
        assertNotNull(arrayPermutationController);
    }

}