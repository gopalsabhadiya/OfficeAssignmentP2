package com.infy.movieapp.controller;

import com.infy.movieapp.bean.Movie;
import com.infy.movieapp.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/highestratedmovie")
public class MovieController {

    @Autowired
    private RatingService ratingService;

    @GetMapping
    public Movie getHighestRatedMovie(){
        return ratingService.getHighestRatedMovie();
    }

}
