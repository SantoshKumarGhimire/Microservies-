package com.microservice.example.movieratingservice.resources;

import com.microservice.example.movieratingservice.models.Rating;
import com.microservice.example.movieratingservice.models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingResource {

    @RequestMapping("{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId) {
        return new Rating(movieId, 5);
    }
    @RequestMapping("users/{movieId}")
    public UserRating getUserRating(@PathVariable("movieId") String movieId) {
        List<Rating> ratings = Arrays.asList(
                new Rating("123", 5),
                new Rating("345", 5),
                new Rating("567", 7)
        );
        UserRating userRating = new UserRating();
        userRating.setRatingList(ratings);
        return userRating;
    }
}
