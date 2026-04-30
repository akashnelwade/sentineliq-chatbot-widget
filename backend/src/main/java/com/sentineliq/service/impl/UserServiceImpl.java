package com.sentineliq.service;

import com.sentineliq.entity.User;
import com.sentineliq.exception.DuplicateResourceException;
import com.sentineliq.exception.ResourceNotFoundException;
import com.sentineliq.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {

        //  Validation
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }

        //  Duplicate check
        userRepository.findByEmail(user.getEmail())
                .ifPresent(u -> {
                    throw new DuplicateResourceException("Email already exists");
                });

        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + id)
                );
    }

    @Override
    public void deleteUser(Long id) {

        User user = getUserById(id);
        userRepository.delete(user);
    }
}