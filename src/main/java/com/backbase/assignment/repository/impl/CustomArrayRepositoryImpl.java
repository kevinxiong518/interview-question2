package com.backbase.assignment.repository.impl;

import com.backbase.assignment.entity.ArrayEntity;
import com.backbase.assignment.repository.CustomArrayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CustomArrayRepositoryImpl implements CustomArrayRepository {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<ArrayEntity> findArraysByNumbersString(String numbersString) {
        return entityManager.createNativeQuery(
                "SELECT * FROM ARRAYS WHERE numbers_string = :numbersString", ArrayEntity.class)
                .setParameter("numbersString", numbersString)
                .getResultList();
    }
}
