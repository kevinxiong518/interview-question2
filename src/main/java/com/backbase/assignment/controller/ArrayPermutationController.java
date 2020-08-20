package com.backbase.assignment.controller;

import com.backbase.assignment.exception.MissingNumbersException;
import com.backbase.assignment.service.ArrayPermutationService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArrayPermutationController {

    @Autowired
    private ArrayPermutationService arrayPermutationService;

    @GetMapping("/store")
    public String storeArray(@RequestParam(name = "numbers") String numbers) {
        if (Strings.isEmpty(numbers))
            throw new MissingNumbersException("numbers parameter is required.");

//        return arrayPermutationService.storeArrayInDB(numbers);
        return arrayPermutationService.storeArrayInDB(numbers);
    }

    @GetMapping("/permutation")
    public String getPermutationById(@RequestParam(name = "id") long id) {
//        return arrayPermutationService.getPermutationById(id);
        return arrayPermutationService.getPermutationByIdFromDB(id);
    }

}
