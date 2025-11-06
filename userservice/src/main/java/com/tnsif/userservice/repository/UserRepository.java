package com.tnsif.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tnsif.userservice.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
