package com.example.users;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private Map<UUID, User> users = new ConcurrentHashMap<>();

    @GetMapping
    public List<User> getAllUsers(@RequestParam(required = false) String status) {
        if (status == null) {
            return new ArrayList<>(users.values());
        }
        // Filter users by status optional
        return users.values().stream()
                .filter(user -> status.equals(user.getStatus()))
                .collect(Collectors.toList());
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        // Logic to create a new user
        user.setId(UUID.randomUUID());
        user.setCreatedAt(Instant.now().toString());
        user.setUpdatedAt(Instant.now().toString());
        users.put(user.getId(), user);
        return user;
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable UUID id, @RequestBody User user) {
        // Logic to update an existing user
        user.setId(id);
        user.setUpdatedAt(Instant.now().toString());
        users.put(id, user);
        return user;
    }
}
