package com.todoNuraliev.demo.controllers;


import com.todoNuraliev.demo.models.Category;
import com.todoNuraliev.demo.models.Task;
import com.todoNuraliev.demo.models.User;
import com.todoNuraliev.demo.repositories.CategoryRepo;
import com.todoNuraliev.demo.repositories.TaskRepo;
import com.todoNuraliev.demo.repositories.UserRepo;
import com.todoNuraliev.demo.services.CategoryService;
import com.todoNuraliev.demo.services.TaskService;
import com.todoNuraliev.demo.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TaskController {
    private final TaskService taskService;

    private final UserService userService;

    private final CategoryService categoryService;

    public TaskController(TaskRepo taskRepo, UserRepo userRepo, CategoryRepo categoryRepo) {
        this.taskService = new TaskService(taskRepo);
        this.categoryService = new CategoryService(categoryRepo);
        this.userService = new UserService(userRepo);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Task> getAll() {
        return taskService.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/tasks/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Task getById(@PathVariable Long taskId) {
        return taskService.getById(taskId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/users/{userId}/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Task> getByUser(@PathVariable Long userId) {
        return taskService.getByUserId(userId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/users/{userId}/categories/{categoryId}/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Task> getByCategoryAndUser(@PathVariable Long userId, @PathVariable Long categoryId) {
        Category category = categoryService.getById(categoryId);
        User user = userService.getById(userId);
        return taskService.getByUserAndCategory(user, category);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/users/{userId}/categories/{categoryId}/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
    public Task create(@PathVariable Long userId, @PathVariable Long categoryId, @RequestBody Task task) {
        User user = userService.getById(userId);
        Category category = categoryService.getById(categoryId);
        return taskService.add(user, category, task);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/users/{userId}/categories/{categoryId}/tasks/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Task updateTask(@PathVariable Long userId,
                           @PathVariable Long taskId,
                           @PathVariable Long categoryId,
                           @Valid @RequestBody Task taskRequest) {
        Task task = taskService.getById(taskId);
        User user = userService.getById(userId);
        Category category = categoryService.getById(categoryId);
        return taskService.update(user, category, task, taskRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/tasks/{taskId}")
    public void delete(@PathVariable Long taskId) {
        taskService.delete(taskService.getById(taskId));
    }
}
