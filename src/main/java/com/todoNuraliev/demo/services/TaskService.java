package com.todoNuraliev.demo.services;

import com.todoNuraliev.demo.models.Category;
import com.todoNuraliev.demo.models.Task;
import com.todoNuraliev.demo.models.User;
import com.todoNuraliev.demo.repositories.TaskRepo;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TaskService {
    private final TaskRepo taskRepo;

    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public List<Task> getAll() {
        return taskRepo.findAll();
    }
    public List<Task> getByUserId(Long userId) {
        return taskRepo.findAllByUser_Id(userId);
    }
    public List<Task> getByUserAndCategory(User user, Category category) {
        return taskRepo.findAllByUserIdAndCategoryId(user.getId(), category.getId());
    }

    public Task getById(Long id) throws ResourceNotFoundException {
        return taskRepo.findTaskById(id).orElseThrow(
                () -> new ResourceNotFoundException("Task not found with id: " + id)
        );
    }

    public Task add(User user, Category category, Task task) {
        Date date = new Date();
        task.setCreated_at(date);
        task.setUpdated_at(date);
        task.setUser(user);
        task.setCategory(category);
        return taskRepo.saveAndFlush(task);
    }

    public Task update(User user, Category category, Task task, Task taskRequest) {
        task.setUpdated_at(new Date());
        task.setName(taskRequest.getName());
        task.setDescription(taskRequest.getDescription());
        task.setComplete(taskRequest.getComplete());
        task.setUser(user);
        task.setCategory(category);
        return taskRepo.saveAndFlush(task);
    }

    public void delete(Task task) {
        taskRepo.findTaskById(task.getId()).ifPresent(taskRepo::delete);
    }
}
