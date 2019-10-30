package com.microservice.example.movieinfoservice.resources;

import com.microservice.example.movieinfoservice.models.Movie;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieResource {
    @RequestMapping("/{movieId}")
    public Movie getMovieItem(@PathVariable("movieId") String movieId){
        return new Movie("123","transformer");
    }
}
