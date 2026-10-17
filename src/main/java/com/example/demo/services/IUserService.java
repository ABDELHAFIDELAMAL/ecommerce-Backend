package com.example.demo.services;

import com.example.demo.entities.User;

import java.util.List;

public interface IUserService {
    User createUser(User user);
    List<User> getAllUsers();
    User findUserByUserName(String username);
}
