package com.microservice.example.moviecatalogservice.resources;

import com.microservice.example.moviecatalogservice.models.*;
import com.microservice.example.moviecatalogservice.service.MovieSummaryService;
import com.microservice.example.moviecatalogservice.service.UserRatingService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    MovieSummaryService movieSummaryService;
    @Autowired
    UserRatingService userRatingService;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCaltalogItems(@PathVariable("userId") String userId) {
        UserRating ratings = userRatingService.getUserRating(userId);
        return ratings.getRatingList().stream().map(rating -> { MovieSummary movie = movieSummaryService.getMovieSummary(rating);
            return new CatalogItem(movie.getOriginal_title(), movie.getOverview(), rating.getRating());
        }).collect(Collectors.toList());
    }
}
