package com.example.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private java.util.UUID id;
    private String name;
    private int age;
    private String email;
    private String status;
    private String createdAt;
    private String updatedAt;
}