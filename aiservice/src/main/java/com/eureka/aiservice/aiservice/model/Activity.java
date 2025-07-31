package com.eureka.aiservice.aiservice.model;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Map;

@Data
public class Activity {
    private String id;
    private String userId;
    private String type;
    private ActivityType activityType;
    private int caloriesBurned;
    private LocalDateTime startTime;
    private Map<String, Object> additionalMetrics;
    private Integer duration;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
