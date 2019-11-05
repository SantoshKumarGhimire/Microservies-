package com.microservice.example.moviecatalogservice.service;

import com.microservice.example.moviecatalogservice.models.Rating;
import com.microservice.example.moviecatalogservice.models.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.bouncycastle.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserRatingService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackUserRating"
            , commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
    })

    public UserRating getUserRating(String userId) {
        return restTemplate.getForObject("http://movie-rating-service/ratingsdata/users/" + userId, UserRating.class);
    }

    public UserRating getFallbackUserRating(String userId) {
        List<Rating> ratings = new ArrayList<>();
        ratings.add(new Rating("123", 0));
        UserRating userRating = new UserRating(ratings);
        return userRating;
    }
}
