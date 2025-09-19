package com.eureka.aiservice.aiservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "recommendations")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recommendation {
    private String id;
    private String activityId;
    private String userId;
    private ActivityType activityType;
    private String recommendation;
    private List<String> improvements;
    private List<String> suggestions;
    private List<String> safety;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @CreatedDate
    private LocalDateTime createdAt;

}
