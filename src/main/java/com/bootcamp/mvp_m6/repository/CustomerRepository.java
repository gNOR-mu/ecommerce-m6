package com.bootcamp.mvp_m6.repository;

import com.bootcamp.mvp_m6.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
