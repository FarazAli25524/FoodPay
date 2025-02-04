package com.foodpay.repository;

import com.foodpay.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,  Long> {
    public User findByEmail(String email);
    public User findByEmailAndPassword(String email, String password);
    public User findByName(String name);
}
