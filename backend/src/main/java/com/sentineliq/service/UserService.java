package com.sentineliq.service;

import com.sentineliq.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    User createUser(User user);

    Page<User> getAllUsers(Pageable pageable);

    User getUserById(Long id);

    User updateUser(Long id, User user);

    void deleteUser(Long id);

    List<User> searchUsers(String q);
}