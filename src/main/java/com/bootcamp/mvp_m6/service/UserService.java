package com.bootcamp.mvp_m6.service;

import com.bootcamp.mvp_m6.model.User;
import com.bootcamp.mvp_m6.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public Optional<User> findByEmail(String email){
        return  userRepository.findByEmail(email);
    }

    public void register(){

    }
}
