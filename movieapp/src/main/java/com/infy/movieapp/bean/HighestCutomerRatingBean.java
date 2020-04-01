package com.infy.movieapp.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HighestCutomerRatingBean {

    private Integer id;
    private String firstName;
    private String lastName;
    private Double customerAverageRating;
    private Double averageRating;
}
