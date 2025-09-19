package com.eureka.aiservice.aiservice.controller;

import com.eureka.aiservice.aiservice.model.Recommendation;
import com.eureka.aiservice.aiservice.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recommendations")
public class RecommendationController {
    private final RecommendationService recommendationService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Recommendation>> getUserRecommendations(@PathVariable String userId) {
        List<Recommendation> recommendations = recommendationService.getRecommendationsForUser(userId);

        return ResponseEntity.ok(recommendations);
    }

    @GetMapping("/activity/{activityId}")
    public ResponseEntity<Recommendation> getActivityRecommendations(@PathVariable String activityId) {
        Recommendation recommendations = recommendationService.getRecommendationsForActivity(activityId);

        return ResponseEntity.ok(recommendations);
    }
}