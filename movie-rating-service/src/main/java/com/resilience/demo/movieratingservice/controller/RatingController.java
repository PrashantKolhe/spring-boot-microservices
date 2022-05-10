package com.resilience.demo.movieratingservice.controller;

import com.resilience.demo.movieratingservice.models.Rating;
import com.resilience.demo.movieratingservice.models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/ratingsdata")
public class RatingController {

    @RequestMapping("{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId){

        return new Rating("name",4);
    }

    @RequestMapping("/users/{userId}")
    public UserRating getUserRating(@PathVariable("userId") String userId){
        return new UserRating(Arrays.asList(new Rating("1234",1),
                new Rating("5678",2)));
    }
}
