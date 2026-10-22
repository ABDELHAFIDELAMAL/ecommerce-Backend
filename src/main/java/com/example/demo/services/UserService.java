
package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.exceptions.AlReadyExistException;
import com.example.demo.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found with id: " + userId));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email.trim().toLowerCase());
    }

    @Override
    @Transactional
    public User updateUser(Long userId, User user) {
        User existingUser = getUserById(userId);

        String email = user.getEmail().trim().toLowerCase();

        if (!existingUser.getEmail().equals(email)
                && userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already exists: " + email);
        }

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(email);

        if (user.getPassword() != null && !user.getPassword().isBlank()) {
            existingUser.setPassword(
                    passwordEncoder.encode(user.getPassword())
            );
        }

        existingUser.setRole(user.getRole());

        return userRepository.save(existingUser);
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        User user = getUserById(userId);
        userRepository.delete(user);
    }


    @Override
    @Transactional
    public User createUser(User user) throws AlReadyExistException {
        String email = user.getEmail().trim().toLowerCase();

        if (userRepository.existsByEmail(email)) {
            throw new AlReadyExistException(
                    "User with this email already exists!"
            );
        }

        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }
}
