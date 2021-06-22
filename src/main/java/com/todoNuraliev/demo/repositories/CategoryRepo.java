package com.todoNuraliev.demo.repositories;

import com.todoNuraliev.demo.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    boolean existsByName(String name);

    Optional<Category> findCategoryById(Long id);
}