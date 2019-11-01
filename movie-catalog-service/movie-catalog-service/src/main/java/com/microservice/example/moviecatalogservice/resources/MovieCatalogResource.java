package com.microservice.example.moviecatalogservice.resources;

import com.microservice.example.moviecatalogservice.models.CatalogItem;
import com.microservice.example.moviecatalogservice.models.Movie;
import com.microservice.example.moviecatalogservice.models.Rating;
import com.microservice.example.moviecatalogservice.models.UserRating;
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

        UserRating ratings = restTemplate.getForObject("http://localhost:8090/ratingsdata/users/"+userId,UserRating.class);

        return ratings.getRatingList().stream().map(rating -> {
            Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);

            return new CatalogItem(movie.getMovieName(), "abcd", rating.getRating());
        }).collect(Collectors.toList());

    }

}
