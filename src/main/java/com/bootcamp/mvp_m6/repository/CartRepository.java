package com.bootcamp.mvp_m6.repository;

import com.bootcamp.mvp_m6.model.Cart;
import com.bootcamp.mvp_m6.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}
