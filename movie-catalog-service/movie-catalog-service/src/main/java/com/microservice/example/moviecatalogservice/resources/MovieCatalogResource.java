package com.microservice.example.moviecatalogservice.resources;

import com.microservice.example.moviecatalogservice.models.*;
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
    RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCaltalogItems(@PathVariable("userId") String userId) {
      /*  List<Rating> ratings = Arrays.asList(
                new Rating("123", 5),
                new Rating("345", 5),
                new Rating("567", 7)
        );*/

        UserRating ratings = restTemplate.getForObject("http://movie-rating-service/ratingsdata/users/"+userId,UserRating.class);

        return ratings.getRatingList().stream().map(rating -> {
            MovieSummary movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), MovieSummary.class);

            return new CatalogItem(movie.getOriginal_title(), movie.getOverview(), rating.getRating());
        }).collect(Collectors.toList());

    }

}
