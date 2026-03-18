package com.bootcamp.mvp_m6.service;

import com.bootcamp.mvp_m6.model.Customer;
import com.bootcamp.mvp_m6.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;


    public Optional<Customer> findByEmail(String email){
        return  customerRepository.findByEmail(email);
    }
}
