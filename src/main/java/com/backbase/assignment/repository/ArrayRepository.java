package com.backbase.assignment.repository;

import com.backbase.assignment.entity.ArrayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ArrayRepository extends JpaRepository<ArrayEntity, Long>, CustomArrayRepository {
}
