package com.example.bankmanagementsystem.controller;
import com.example.bankmanagementsystem.dto.LoginResponse;
import com.example.bankmanagementsystem.entity.User;
import com.example.bankmanagementsystem.service.UserService;
import com.example.bankmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.bankmanagementsystem.dto.LoginRequest;
import org.springframework.http.ResponseEntity;
@RestController
@RequestMapping("/api/users")
//@Tag(name = "User APIs")
@CrossOrigin("*")
public class UserController {
    private final UserService userService;
private final UserRepository userRepository;
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;}
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    //@Operation(summary = "Login User")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }
    @GetMapping
    public List<User> getAllUsers() {

        return userRepository.findAll();

    }
    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username) {

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

    }
    @PutMapping("/{username}")
    public User updateUser(
            @PathVariable String username,
            @RequestBody User updatedUser) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        user.setFullName(updatedUser.getFullName());
        user.setEmail(updatedUser.getEmail());
        user.setMobile(updatedUser.getMobile());
        user.setAddress(updatedUser.getAddress());

        return userRepository.save(user);
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {

        userRepository.deleteById(id);

        return "User Deleted Successfully";
    }
    @GetMapping("/count")
    public Long getUserCount() {

        return userRepository.count();

    }
}