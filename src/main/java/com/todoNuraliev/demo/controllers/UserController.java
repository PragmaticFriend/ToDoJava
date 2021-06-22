package com.todoNuraliev.demo.controllers;


import com.todoNuraliev.demo.models.User;
import com.todoNuraliev.demo.repositories.UserRepo;
import com.todoNuraliev.demo.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserRepo userRepo) {
        this.userService = new UserService(userRepo);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        return userService.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/users/id/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserById(@PathVariable Long userId) {
        return userService.getById(userId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/users/login/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserByLogin(@PathVariable String login) {
        return userService.getByLogin(login);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public User registration(@Valid @RequestBody User user) {
        return userService.add(user);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/authorization", produces = MediaType.APPLICATION_JSON_VALUE)
    public User authorization(@Valid @RequestBody User user) {
        return userService.authorization(user);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User update(@PathVariable Long userId, @Valid @RequestBody User userRequest) {
        return userService.update(userId, userRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("users/{userId}")
    public void delete(@PathVariable Long userId) {
        userService.delete(userService.getById(userId));
    }
}
