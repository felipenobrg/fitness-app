package com.fitness.userservice.activityservice.repository;

import com.fitness.userservice.activityservice.model.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActivityRepository extends MongoRepository<Activity,String> {
}
