package com.backbase.assignment.service.impl;

import com.backbase.assignment.exception.ArrayNotFoundException;
import com.backbase.assignment.entity.ArrayEntity;
import com.backbase.assignment.repository.ArrayRepository;
import com.backbase.assignment.service.ArrayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArrayServiceImpl implements ArrayService {

    @Autowired
    private ArrayRepository arrayRepository;

    @Override
    public ArrayEntity saveArray(ArrayEntity arrayEntity) {
        List<ArrayEntity> arrayEntityList = arrayRepository.findArraysByNumbersString(arrayEntity.getNumbersString());
        if(arrayEntityList.isEmpty())
            return arrayRepository.save(arrayEntity);

        return arrayEntityList.get(0);
    }

    @Override
    public ArrayEntity findArrayById(long id) {
        Optional<ArrayEntity> optional = arrayRepository.findById(id);
        if(!optional.isPresent())
            throw new ArrayNotFoundException("Cannot find array with id " + id);

        return optional.get();
    }

}
