package com.winston.practice.repository;

import com.winston.practice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository2 extends JpaRepository<Customer, Long> {

}
