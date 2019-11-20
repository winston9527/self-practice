package com.winston.practice;

import com.winston.practice.entity.AccountEntity;
import com.winston.practice.entity.UserEntity;
import com.winston.practice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;

@SpringBootTest
public class UserTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void testSave() {

        UserEntity userEntity = UserEntity.builder()
          .name("王小红")
          .age(12)
          .build();

        AccountEntity accountEntity = AccountEntity.builder().
          accountName("可用账户").userEntity(userEntity)
          .availableCurl(BigDecimal.valueOf(Math.random() * 10))
          .build();

        AccountEntity accountEntity2 = AccountEntity.builder().
          accountName("冻结账户").userEntity(userEntity)
          .availableCurl(BigDecimal.valueOf(Math.random() * 10))
          .build();

        HashSet<AccountEntity> accounts = new HashSet<>();
        accounts.add(accountEntity);
        accounts.add(accountEntity2);
        userEntity.setAccountEntities(accounts);

        userRepository.save(userEntity);
    }

    @Transactional
    @Test
    public void testFindAll() {
        List<UserEntity> all = userRepository.findAll();
        all.forEach(userEntity->{

            System.err.println("=============");
            System.err.println(userEntity);
            userEntity.getAccountEntities()
              .forEach(s->{
                  System.err.println("账户："+s);
                  System.err.println(s.getAccountDetailEntities());
              });
        });
    }


}
