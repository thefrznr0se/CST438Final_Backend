package com.cst438.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movie_id;

    private String movie_title;
    private String movie_rating;
    private int movie_length;

    @ManyToOne
    @JoinColumn(name = "price_id")
    private Price price;

    @OneToMany(mappedBy = "movie")
    private List<Schedule> schedules;

    // Add getters and setters

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public String getMovie_title() {
        return movie_title;
    }

    public void setMovie_title(String movie_title) {
        this.movie_title = movie_title;
    }

    public String getMovie_rating() {
        return movie_rating;
    }

    public void setMovie_rating(String movie_rating) {
        this.movie_rating = movie_rating;
    }

    public int getMovie_length() {
        return movie_length;
    }

    public void setMovie_length(int movie_length) {
        this.movie_length = movie_length;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}
