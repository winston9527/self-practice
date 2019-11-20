package com.winston.practice.repository;

import com.winston.practice.entity.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

   /* @Override
    @EntityGraph(value = "user.accountEntities", type = EntityGraph.EntityGraphType.FETCH)
    List<UserEntity> findAll();*/

    @Override
    @EntityGraph(value = "user.accountEntities2", type = EntityGraph.EntityGraphType.FETCH)
    List<UserEntity> findAll();
}
