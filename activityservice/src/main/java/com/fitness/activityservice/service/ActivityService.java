package com.fitness.activityservice.service;

import com.fitness.activityservice.dto.ActivityRequest;
import com.fitness.activityservice.dto.ActivityResponse;
import com.fitness.activityservice.model.Activity;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class ActivityService {



    @Autowired
    ActivityRepository activityRepository;

    public ActivityResponse trackActivity(ActivityRequest req) {
        Activity activity = Activity.builder().
                userId(req.getUserId()).
                type(req.getType()).
                duration(req.getDuration()).
                caloriesBurned(req.getCaloriesBurned()).
                startTime(req.getStartTime()).
                additionalMetrics(req.getAdditionalMetrics()).build();

        Activity savedActivity = activityRepository.save(activity);
        return mapToResponse(savedActivity);


    }
    private ActivityResponse mapToResponse(Activity activity){
        ActivityResponse response = new ActivityResponse();
        response.setId(activity.getId());
        response.setUserId(activity.getUserId());
        response.setType(activity.getType());
        response.setDuration(activity.getDuration());
        response.setCaloriesBurned(activity.getCaloriesBurned());
        response.setStartTime(activity.getStartTime());
        response.setAdditionalMetrics(activity.getAdditionalMetrics());
        response.setCreatedAt(activity.getCreatedAt());
        response.setUpdatedAt(activity.getUpdatedAt());
        return response;
    }
}
