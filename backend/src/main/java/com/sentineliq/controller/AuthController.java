package com.sentineliq.controller;

import com.sentineliq.entity.User;
import com.sentineliq.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import com.sentineliq.security.CustomUserDetailsService;
import com.sentineliq.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        user.setRole("USER");
        userService.createUser(user);   // Save user to database
        return "User registered";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email) {
        return jwtUtil.generateToken(email);
    }

    @PostMapping("/refresh")
    public String refresh(@RequestParam String email) {
        return jwtUtil.generateToken(email);
    }
}