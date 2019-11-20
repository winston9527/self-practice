package com.winston.practice;

import com.winston.practice.entity.UserEntity;
import com.winston.practice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {
        userRepository.save(UserEntity.builder().age(22).build());
    }

}
