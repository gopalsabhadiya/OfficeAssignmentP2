package com.infy.movieapp.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class Movie {

    private Integer movieId;
    private Double averageRating;
}
