
package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.exceptions.AlReadyExistException;

import java.util.List;

public interface IUserService {

    User createUser(User user) throws AlReadyExistException;
    User getUserById(Long id);
    User getUserByEmail(String email);
    List<User> getAllUsers();
    User updateUser(Long id, User user);
    void deleteUser(Long id);
}
