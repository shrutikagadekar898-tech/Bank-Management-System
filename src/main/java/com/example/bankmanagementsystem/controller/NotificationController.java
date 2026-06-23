package com.example.bankmanagementsystem.controller;

import com.example.bankmanagementsystem.entity.Notification;
import com.example.bankmanagementsystem.repository.NotificationRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/notifications")
@CrossOrigin("*")
public class NotificationController {

    private final NotificationRepository notificationRepository;

    public NotificationController(
            NotificationRepository notificationRepository) {

        this.notificationRepository = notificationRepository;
    }

    @GetMapping("/user/{username}")
    public List<Notification> getUserNotifications(
            @PathVariable String username) {

        return notificationRepository.findByUsername(username);
    }
}