package com.example.bankmanagementsystem.service;

import com.example.bankmanagementsystem.dto.LoginRequest;
import com.example.bankmanagementsystem.dto.LoginResponse;
import com.example.bankmanagementsystem.entity.User;
import com.example.bankmanagementsystem.repository.UserRepository;
import com.example.bankmanagementsystem.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository repository,
                       BCryptPasswordEncoder passwordEncoder) {

        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    // Register User
    public User register(User user) {

        // Username already exists
        if (repository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        // Encrypt password
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        return repository.save(user);
    }

    // Login User
    public LoginResponse login(LoginRequest request) {

        System.out.println("Username entered = " + request.getUsername());

        User user = repository.findByUsername(request.getUsername())
                .orElse(null);

        System.out.println("User found = " + user);

        if (user != null) {

            System.out.println("Entered Password = " + request.getPassword());
            System.out.println("DB Password = " + user.getPassword());

            boolean match = passwordEncoder.matches(
                    request.getPassword(),
                    user.getPassword());

            System.out.println("Password Match = " + match);

            if (match) {

              //  String token = JwtUtil.generateToken(user.getUsername());
                String token = JwtUtil.generateToken(user.getUsername());

                return new LoginResponse(
                        token,
                        user.getRole().name(),
                        user.getUsername()
                );
            }
        }

        throw new RuntimeException("Invalid Credentials");
    }
}