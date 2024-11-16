package com.dailycodework.dream_shops.repository;

import com.dailycodework.dream_shops.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


    boolean existsByEmail(String email);
}
