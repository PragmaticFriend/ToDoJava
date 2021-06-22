package com.todoNuraliev.demo.services;


import com.todoNuraliev.demo.models.User;
import com.todoNuraliev.demo.repositories.UserRepo;
import lombok.extern.log4j.Log4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Log4j
@Service
@Transactional
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) { this.userRepo = userRepo; }
    public List<User> getAll() {
        return userRepo.findAll();
    }

    public User getById(Long id) {
        return null;
    }

    public User getByLogin(String login){
        return null;
    }


    public User add(User user) throws ResourceNotFoundException {
        Date date = new Date();
        user.setCreated_at(date);
        user.setUpdated_at(date);
        return userRepo.saveAndFlush(user);
    }

    public User authorization(User user) throws ResourceNotFoundException {
        User new_user = userRepo.findUserByLogin(user.getLogin())
                .orElseGet(() -> userRepo.findUserByEmail(user.getEmail()).
                        orElseThrow(() -> new ResourceNotFoundException("User not found")));
        if (new_user.getPassword().equals(user.getPassword()))
            return new_user;
        else
            throw new ResourceNotFoundException("User not found");
    }

    public User update(Long userId, User userRequest) throws ResourceNotFoundException {
        User user = userRepo.findUserById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User not found with id: " + userId)
        );
        user.setUpdated_at(new Date());
        user.setLogin(userRequest.getLogin());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        return userRepo.saveAndFlush(user);
    }

    public void delete(User user) {
        userRepo.findUserByLogin(user.getLogin()).ifPresent(userRepo::delete);
    }
}
