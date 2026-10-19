
package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.exceptions.AlReadyExistException;

import java.util.List;

public interface IUserService {

    User createUser(User user) throws AlReadyExistException;

    List<User> getAllUsers();

    User getUserById(Long userId);

    User getUserByEmail(String email);

    User updateUser(Long userId, User user);

    void deleteUser(Long userId);

    boolean existsByEmail(String email);
}
