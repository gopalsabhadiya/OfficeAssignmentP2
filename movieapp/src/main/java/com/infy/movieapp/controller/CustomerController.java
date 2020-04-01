package com.infy.movieapp.controller;

import com.infy.movieapp.bean.HighestCutomerRatingBean;
import com.infy.movieapp.entity.Rating;
import com.infy.movieapp.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private RatingService ratingService;

    @GetMapping("/{customerId:[\\d]+}/rate/{rating:[\\d]+}/{movieId:[\\d]+}")
    public Rating addRating(@PathVariable("customerId") Integer customerId,
                            @PathVariable("rating") Integer rating,
                            @PathVariable("movieId") Integer movieId){

        return ratingService.addRating(rating, customerId, movieId);
    }

    @GetMapping
    public List<Rating> getAllRating(){
        return ratingService.getAllRating();
    }

    @GetMapping("/gethighestcutomerrating")
    public HighestCutomerRatingBean getHighestAverageCustomerRating(){
        return ratingService.getHighestAverageCustomerRating();
    }

}
