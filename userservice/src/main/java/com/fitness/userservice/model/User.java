package com.fitness.userservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    private String id;
    private String name;
    private String email;
    private String firstName;
    private String lastName;
    private UserRole role = UserRole.USER;
}
