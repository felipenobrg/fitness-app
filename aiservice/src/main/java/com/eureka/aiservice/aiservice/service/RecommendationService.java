package com.eureka.aiservice.aiservice.service;

import com.eureka.aiservice.aiservice.model.Recommendation;
import com.eureka.aiservice.aiservice.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {
    private final RecommendationRepository recommendationRepository;

    public List<Recommendation> getRecommendationsForUser(String userId) {
      return recommendationRepository.findByUserId(userId);
    }

    public Recommendation getRecommendationsForActivity(String activityId) {
        return recommendationRepository.findByActivityId(activityId)
                .orElseThrow(() -> new RuntimeException("No recommendations found for activity: " + activityId));
    }
}
