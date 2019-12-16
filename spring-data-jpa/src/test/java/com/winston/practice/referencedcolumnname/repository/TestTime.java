package com.winston.practice.referencedcolumnname.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

class TestTime {
    @Test
    public void testTime(){
        LocalDateTime dt = LocalDateTime.now();
        ZonedDateTime localDateTime2 = dt.atZone(ZoneId.of("Asia/Shanghai"));
        ZonedDateTime localDateTime3 = dt.atZone(ZoneId.of("Asia/Shanghai"));

        System.out.println(dt);
        System.out.println(localDateTime2);
        System.out.println(localDateTime3);
    }

}