package com.fitness.userservice.controller;

import com.fitness.userservice.dto.UserRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserProfile(@PathVariable String userId) {

        return ResponseEntity.ok(userService.getUserProfile(userId));
    }

    @PostMapping("register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRequest userRequest) {
        System.out.println("Registering user: " + userRequest.getEmail());

        return ResponseEntity.ok(userService.register(userRequest));
    }

    @GetMapping("/{userId}/validate")
    public ResponseEntity<Boolean> validateUser(@PathVariable String userId) {

        return ResponseEntity.ok(userService.existByUserId(userId));
    }
}
