package com.resilience.demo.moviecatalogservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.resilience.demo.moviecatalogservice.models.Movie;
import com.resilience.demo.moviecatalogservice.models.Rating;
import com.resilience.demo.moviecatalogservice.models.UserRating;
import com.resilience.demo.moviecatalogservice.service.MovieInfo;
import com.resilience.demo.moviecatalogservice.service.UserRatingInfo;
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
    MovieInfo movieInfo;

    @Autowired
    UserRatingInfo userRatingInfo;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){

        //Retrieve User Rating
        UserRating ratings= userRatingInfo.getUserRating(userId);

        return ratings.getRatings().stream().map(
                rating -> movieInfo.getCatalogItem(rating))
         .collect(Collectors.toList());
    }
}
