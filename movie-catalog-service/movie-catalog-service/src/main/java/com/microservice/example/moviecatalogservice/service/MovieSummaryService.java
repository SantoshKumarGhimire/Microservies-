package com.microservice.example.moviecatalogservice.service;

import com.microservice.example.moviecatalogservice.models.MovieSummary;
import com.microservice.example.moviecatalogservice.models.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieSummaryService {
    @Autowired
    RestTemplate restTemplate;
    @HystrixCommand(fallbackMethod = "getFallbackMovieSummary")
    public MovieSummary getMovieSummary(Rating rating) {
        return restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), MovieSummary.class);
    }

    public MovieSummary getFallbackMovieSummary(Rating rating) {
        MovieSummary movieSummary = new MovieSummary();
        movieSummary.setId(123);
       // movieSummary.
        return movieSummary;
    }
}
