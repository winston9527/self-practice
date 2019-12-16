package com.winston.practice.referencedcolumnname.repository;

import com.winston.practice.referencedcolumnname.entity.ScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<ScoreEntity,Long> {
}
