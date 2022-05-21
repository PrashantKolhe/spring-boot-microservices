package com.resilience.demo.moviecatalogservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.resilience.demo.moviecatalogservice.models.CatalogItem;
import com.resilience.demo.moviecatalogservice.models.Movie;
import com.resilience.demo.moviecatalogservice.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfo {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
    public CatalogItem getCatalogItem(Rating rating) {
        Movie movie= restTemplate.getForObject("http://MOVIE-INFO-SERVICE/movies/"+ rating.getMovieId(), Movie.class);
        return new CatalogItem(movie.getName(),"Desc", rating.getRating());
    }
    public CatalogItem getFallbackCatalogItem(Rating rating) {
        return new CatalogItem("No Movie found","Desc", rating.getRating());
    }

}
