package com.todoNuraliev.demo.repositories;

import com.todoNuraliev.demo.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepo extends JpaRepository<Task, Long> {
    List<Task> findAllByUserIdAndCategoryId(Long userId, Long categoryId);

    List<Task> findAllByUser_Id(Long user_id);

    Optional<Task> findTaskById(Long id);
}
