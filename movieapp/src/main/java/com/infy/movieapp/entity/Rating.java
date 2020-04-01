package com.infy.movieapp.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer rating;

    private Integer movieId;

    private Integer customerId;
}
