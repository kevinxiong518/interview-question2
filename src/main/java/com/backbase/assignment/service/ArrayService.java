package com.backbase.assignment.service;

import com.backbase.assignment.entity.ArrayEntity;

public interface ArrayService {
    ArrayEntity saveArray(ArrayEntity arrayEntity);

    ArrayEntity findArrayById(long id);
}
