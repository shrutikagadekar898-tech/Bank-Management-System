package com.example.bankmanagementsystem.repository;

import com.example.bankmanagementsystem.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NotificationRepository
        extends JpaRepository<Notification,Long> {

    List<Notification> findByUsername(String username);

}