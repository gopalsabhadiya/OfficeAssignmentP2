package com.infy.movieapp.service;

import com.infy.movieapp.bean.HighestCutomerRatingBean;
import com.infy.movieapp.entity.Rating;
import com.infy.movieapp.bean.Movie;
import com.infy.movieapp.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public Rating addRating(Integer rating, Integer customerId, Integer movieId) {
        Rating r = new Rating();
        r.setRating(rating);
        r.setMovieId(movieId);
        r.setCustomerId(customerId);
        return ratingRepository.save(r);
    }

    public List<Rating> getAllRating() {
        return ratingRepository.findAll();
    }

    public Movie getHighestRatedMovie() {

         Map<Integer, Double> averageRatingOfMovies = ratingRepository.findAll().stream().collect(
                Collectors.groupingBy(
                        rating -> rating.getMovieId(),
                        Collectors.averagingDouble(rating -> rating.getRating())
                )
         );
         return averageRatingOfMovies.entrySet().stream()
                 .sorted(
                         Map.Entry.<Integer, Double>comparingByValue().reversed()
                 )
                 .limit(1)
                 .map(e -> new Movie(e.getKey(), e.getValue()))
                 .collect(Collectors.toList()).get(0);


    }

    public HighestCutomerRatingBean getHighestAverageCustomerRating() {

        Map<Integer, Double> averageCustomerRating = ratingRepository.findAll().stream().collect(
                Collectors.groupingBy(
                        rating -> rating.getCustomerId(),
                        Collectors.averagingDouble(rating -> rating.getRating())
                )
        );
        OptionalDouble average = averageCustomerRating.entrySet().stream().mapToDouble(e -> e.getValue()).average();
        HighestCutomerRatingBean hcrb =  averageCustomerRating.entrySet().stream()
                .sorted(
                        Map.Entry.<Integer, Double>comparingByValue().reversed()
                )
                .limit(1)
                .map(
                        e -> new HighestCutomerRatingBean(e.getKey(), "Gopal", " Sabhadiya", e.getValue(), average.getAsDouble())
                )
                .collect(Collectors.toList()).get(0);

        //If Customer is stored in Database, you have id of that customer in hcrb, fetch the customer and populate relevent fields
        //  Customer c = customerRepository.findById(hcrb.getId());
        //  hcrb.setFirstName(c.getFirstName());
        //  hcrb.setLastName(c.getLastName());

        return hcrb;



    }
}
