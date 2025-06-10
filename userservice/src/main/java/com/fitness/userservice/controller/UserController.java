package com.fitness.userservice.controller;

import com.fitness.userservice.dto.UserRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserProfile(@PathVariable String userId) {

        return ResponseEntity.ok(UserService.getUserProfile(userId));
    }

    @PostMapping("register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRequest userRequest) {

        return ResponseEntity.ok(userRequest.resgister(userRequest));
    }
}
