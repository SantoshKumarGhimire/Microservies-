package com.microservice.example.movieinfoservice.resources;

import com.microservice.example.movieinfoservice.models.Movie;
import com.microservice.example.movieinfoservice.models.MovieSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movies")
public class MovieResource {
    @Value("${api.key}")
    private String apiKey;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/{movieId}")
    public MovieSummary getMovieItem(@PathVariable("movieId") String movieId){
      return  restTemplate.getForObject("https://api.themoviedb.org/3/movie/"+movieId+"?api_key="+apiKey, MovieSummary.class);
//        https://api.themoviedb.org/3/movie/550?api_key=4b1aaeeb4c26808f335e3ea7cf05fce6
//        return new Movie("123","transformer");
    }
}
