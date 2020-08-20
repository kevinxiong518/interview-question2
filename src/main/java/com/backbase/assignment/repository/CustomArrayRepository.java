package com.backbase.assignment.repository;

import com.backbase.assignment.entity.ArrayEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CustomArrayRepository {
    List<ArrayEntity> findArraysByNumbersString(String numbersString);
}
