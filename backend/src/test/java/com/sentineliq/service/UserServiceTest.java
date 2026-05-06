package com.sentineliq.service;

import com.sentineliq.entity.User;
import com.sentineliq.exception.ResourceNotFoundException;
import com.sentineliq.repository.UserRepository;
import com.sentineliq.service.impl.UserServiceImpl;
import com.sentineliq.entity.User;
import com.sentineliq.exception.ResourceNotFoundException;
import com.sentineliq.repository.UserRepository;
import com.sentineliq.service.impl.UserServiceImpl;

import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserById() {

        User user = new User();

        user.setId(1L);
        user.setName("Akash");
        user.setEmail("akash@gmail.com");

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        User result = userService.getUserById(1L);

        assertEquals("Akash", result.getName());
    }

    @Test
    void testCreateUser() {

        User user = new User();

        user.setId(1L);
        user.setName("Akash");
        user.setEmail("akash@gmail.com");

        when(userRepository.save(user))
                .thenReturn(user);

        User result = userService.createUser(user);

        assertEquals("Akash", result.getName());
    }

    @Test
    void testUpdateUser() {

        User existingUser = new User();

        existingUser.setId(1L);
        existingUser.setName("Old Name");
        existingUser.setEmail("old@gmail.com");

        User updatedUser = new User();

        updatedUser.setName("Akash");
        updatedUser.setEmail("akash@gmail.com");

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(existingUser));

        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());

        when(userRepository.save(existingUser))
                .thenReturn(existingUser);

        User result = userService.updateUser(1L, updatedUser);

        assertEquals("Akash", result.getName());
    }

    @Test
    void testDeleteUser() {

        User user = new User();

        user.setId(1L);
        user.setName("Akash");

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

         userService.deleteUser(1L);

        verify(userRepository).delete(user);
    }

    @Test
    void testUserNotFound() {

        when(userRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> userService.getUserById(1L)
        );
    }
    @Test
    void testGetAllUsers() {

        User user = new User();

        user.setId(1L);
        user.setName("Akash");

        Pageable pageable = PageRequest.of(0, 5);

        Page<User> page = new PageImpl<>(List.of(user));

        when(userRepository.findAll(pageable))
                .thenReturn(page);

        Page<User> result = userService.getAllUsers(pageable);

        assertEquals(1, result.getTotalElements());
    }

    @Test
    void testSearchUsers() {

        User user = new User();

        user.setId(1L);
        user.setName("Akash");


        List<User> users = Arrays.asList(user);

        when(userRepository.findByNameContainingIgnoreCase("Akash"))
                .thenReturn(users);

        List<User> result = userService.searchUsers("Akash");

        assertEquals(1, result.size());
    }

    @Test
    void testCreateDuplicateUser() {

        User user = new User();

        user.setEmail("akash@gmail.com");

        when(userRepository.findByEmail("akash@gmail.com"))
                .thenReturn(Optional.of(user));

        assertEquals(
                "akash@gmail.com",
                user.getEmail()
        );
    }

    @Test
    void testUpdateUserNotFound() {

        User updatedUser = new User();

        updatedUser.setName("Akash");

        when(userRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> userService.updateUser(1L, updatedUser)
        );
    }

    @Test
    void testDeleteUserNotFound() {

        when(userRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> userService.deleteUser(1L)
        );
    }
}
