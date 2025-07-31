package com.eureka.aiservice.aiservice.service;

import com.eureka.aiservice.aiservice.model.Activity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityMessageListener {
    private final ActivityAiService activityAiService;


    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void processActivity(Activity activity) {
        log.info("Received activity message for id: {}", activity.getId());
        log.info("Generated Recommendation: {}", activityAiService.generateRecommendation(activity));
    }
}
