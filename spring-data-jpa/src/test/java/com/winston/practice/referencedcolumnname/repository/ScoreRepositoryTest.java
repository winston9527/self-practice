package com.winston.practice.referencedcolumnname.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
class ScoreRepositoryTest {
    @Autowired
    private ScoreRepository scoreRepository;

    @Transactional
    @Test
    public void testFindAll() {
        scoreRepository.findAll().forEach(
          s->{
              System.err.println("结果是："+s);
          }
        );
    }
    @Test
    public void testTime(){
        LocalDateTime dt = LocalDateTime.now();
        System.out.println(dt);
    }

}