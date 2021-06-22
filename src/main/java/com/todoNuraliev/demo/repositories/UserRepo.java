package com.todoNuraliev.demo.repositories;

import com.todoNuraliev.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    boolean existsByLogin(String login);

    boolean existsByEmail(String email);

    Optional<User> findUserById(Long id);

    Optional<User> findUserByLogin(String login);

    Optional<User> findUserByEmail(String email);
}
