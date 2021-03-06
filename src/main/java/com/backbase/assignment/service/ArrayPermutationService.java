package com.backbase.assignment.service;

public interface ArrayPermutationService {
    String storeArray(String numbers);

    String getPermutationById(long id);

    String storeArrayInDB(String numbers);

    String getPermutationByIdFromDB(long id);
}
