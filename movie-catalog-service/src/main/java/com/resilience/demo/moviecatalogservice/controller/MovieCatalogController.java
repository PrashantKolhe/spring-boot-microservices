package com.resilience.demo.moviecatalogservice.controller;

import com.resilience.demo.moviecatalogservice.models.Movie;
import com.resilience.demo.moviecatalogservice.models.Rating;
import com.resilience.demo.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.resilience.demo.moviecatalogservice.models.CatalogItem;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){

        //Retrieve User Rating
        UserRating ratings=  restTemplate.getForObject("http://MOVIE-RATING-SERVICE/ratingsdata/users/"+userId, UserRating.class);

        return ratings.getRatingList().stream().map(rating -> {
            //Retrieve Movie
            //Rest Template Way
            Movie movie= restTemplate.getForObject("http://MOVIE-INFO-SERVICE/movies/"+rating.getMovieId(), Movie.class);

            //Web Client Way
            //Movie movie=webClientBuilder.build().get().uri("http://localhost:8081/movies/"+rating.getMovieId()).retrieve().bodyToMono(Movie.class).block();
            return new CatalogItem(movie.getName(),"Desc",rating.getRating());
        })
         .collect(Collectors.toList());
    }
}
