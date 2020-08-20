package com.backbase.assignment.service.impl;

import com.backbase.assignment.entity.ArrayEntity;
import com.backbase.assignment.exception.ArrayNotFoundException;
import com.backbase.assignment.exception.InvalidNumbersException;
import com.backbase.assignment.model.NumberList;
import com.backbase.assignment.service.ArrayPermutationService;
import com.backbase.assignment.service.ArrayService;
import com.backbase.assignment.util.NumbersUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class ArrayPermutationServiceImpl implements ArrayPermutationService {

    private Map<Long, List<Integer>> idToList;

    @Autowired
    public void setIdToList(Map<Long, List<Integer>> idToList) {
        this.idToList = idToList;
    }

    private Map<String, Long> listStringToId;

    @Autowired
    public void setListStringToId(Map<String, Long> listStringToId) {
        this.listStringToId = listStringToId;
    }

    private static long id = 0L;

    @Autowired
    private ArrayService arrayService;

    @Override
    public String storeArray(String numbers) {
        NumberList numberList = NumbersUtil.convertToList(numbers);
        List<Integer> numList = numberList.getNumList();
        if (numberList.getNumList().isEmpty())
            throw new InvalidNumbersException("Error trying to convert numbers " + numbers);

        String stringValue = numberList.getStringValue();
        if (listStringToId.containsKey(stringValue))
            return String.valueOf(listStringToId.get(stringValue));

        idToList.put(++id, numList);
        listStringToId.put(stringValue, id);
        return String.valueOf(id);
    }

    @Override
    public String getPermutationById(long id) {
        List<Integer> numList = idToList.get(id);
        if (numList == null)
            throw new ArrayNotFoundException("Cannot find array with id " + id);

        List<Integer> permutation = new ArrayList<>(idToList.get(id));
        Collections.shuffle(permutation);
        return permutation.toString();
    }

    @Override
    public String storeArrayInDB(String numbers) {
        NumberList numberList = NumbersUtil.convertToList(numbers);
        ArrayEntity arrayEntity = new ArrayEntity();
        arrayEntity.setNumbers(numberList.getNumList());
        arrayEntity.setNumbersString(numberList.getStringValue());
        return String.valueOf(arrayService.saveArray(arrayEntity).getId());
    }

    @Override
    public String getPermutationByIdFromDB(long id) {
        ArrayEntity arrayEntity = arrayService.findArrayById(id);
        List<Integer> permutation = arrayEntity.getNumbers();
        Collections.shuffle(permutation);
        return permutation.toString();
    }

}
