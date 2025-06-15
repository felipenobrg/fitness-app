package com.fitness.userservice.activityservice.service;

import com.fitness.userservice.activityservice.dto.ActivityRequest;
import com.fitness.userservice.activityservice.dto.ActivityResponse;
import com.fitness.userservice.activityservice.model.Activity;
import com.fitness.userservice.activityservice.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;

    public ActivityResponse trackActivity (ActivityRequest activityRequest) {
        Activity activity = Activity.builder()
                .userId(activityRequest.getUserId())
                .activityType(activityRequest.getActivityType())
                .caloriesBurned(activityRequest.getCaloriesBurned())
                .startTime(activityRequest.getStartTime())
                .additionalMetrics(activityRequest.getAdditionalMetrics())
                .build();

        Activity savedActivity = activityRepository.save(activity);
        return mapToResponse(activity);

    }

    public List<ActivityResponse> getActivityByUserId(String userId) {
        List<Activity> activities = activityRepository.findByUserId(userId);
        return activities.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private ActivityResponse mapToResponse(Activity activity) {
       ActivityResponse activityResponse = new ActivityResponse();
        activityResponse.setId(activity.getId());
        activityResponse.setUserId(activity.getUserId());
        activityResponse.setType(activity.getActivityType());
        activityResponse.setCaloriesBurned(activity.getCaloriesBurned());
        activityResponse.setStartTime(activity.getStartTime());
        activityResponse.setAdditionalMetrics(activity.getAdditionalMetrics());
        activityResponse.setCreatedAt(activity.getCreatedAt());
        activityResponse.setUpdatedAt(activity.getUpdatedAt());
        return activityResponse;
    }
}
