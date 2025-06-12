package com.fitness.userservice.activityservice.controller;


import com.fitness.userservice.activityservice.dto.ActivityRequest;
import com.fitness.userservice.activityservice.dto.ActivityResponse;
import com.fitness.userservice.activityservice.model.Activity;
import com.fitness.userservice.activityservice.service.ActivityService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/activities")
@AllArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityResponse> trackActivity(@RequestBody ActivityRequest activityRequest) {
        return ResponseEntity.ok(activityService.trackActivity(activityRequest));

    }

    @PostMapping("/getActivityById")
    public ResponseEntity<ActivityResponse> getActivityById(@RequestBody String activityId) {
        return ResponseEntity.ok(activityService.getActivityById(activityId));
    }
}
