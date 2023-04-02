package com.dbmsproject.upsideavenue.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dbmsproject.upsideavenue.models.User;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);
}
