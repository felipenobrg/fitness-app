package com.fitness.userservice.activityservice.service;

import com.fitness.userservice.activityservice.dto.ActivityRequest;
import com.fitness.userservice.activityservice.dto.ActivityResponse;
import com.fitness.userservice.activityservice.model.Activity;
import com.fitness.userservice.activityservice.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final UserValidationService userValidationService;

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    public ActivityResponse trackActivity (ActivityRequest activityRequest) {
        boolean isValidUser = userValidationService.validateUser(activityRequest.getUserId());
        if (!isValidUser) {
            throw new IllegalArgumentException("Invalid user ID: " + activityRequest.getUserId());
        }

        Activity activity = Activity.builder()
                .userId(activityRequest.getUserId())
                .activityType(activityRequest.getActivityType())
                .caloriesBurned(activityRequest.getCaloriesBurned())
                .startTime(activityRequest.getStartTime())
                .additionalMetrics(activityRequest.getAdditionalMetrics())
                .build();

        // Publish to RabbitMQ for ai process

        Activity savedActivity = activityRepository.save(activity);

        try {
            rabbitTemplate.convertAndSend(exchange, routingKey, savedActivity);

        } catch (Exception e) {
            throw new RuntimeException("Failed to publish activity to RabbitMQ", e);
        }

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
