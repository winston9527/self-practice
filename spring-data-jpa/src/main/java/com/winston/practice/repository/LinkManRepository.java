package com.winston.practice.repository;

import com.winston.practice.entity.Customer;
import com.winston.practice.entity.LinkMan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkManRepository extends JpaRepository<LinkMan, Long> {

}
