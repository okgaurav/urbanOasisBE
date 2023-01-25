package com.example.urbanoasisproducts.security.Repository;

import com.example.urbanoasisproducts.security.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);
}
