package com.fitness.userservice.activityservice.controller;


import com.fitness.userservice.activityservice.dto.ActivityRequest;
import com.fitness.userservice.activityservice.dto.ActivityResponse;
import com.fitness.userservice.activityservice.model.Activity;
import com.fitness.userservice.activityservice.service.ActivityService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
@AllArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityResponse> trackActivity(@RequestBody ActivityRequest activityRequest) {
        return ResponseEntity.ok(activityService.trackActivity(activityRequest));

    }

    @GetMapping("/getActivityById")
    public ResponseEntity<List<ActivityResponse>> getActivityByUserId(@RequestHeader String userId) {
        return ResponseEntity.ok(activityService.getActivityByUserId(userId));
    }
}
