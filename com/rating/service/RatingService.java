package com.rating.service;

import com.rating.entity.Rating;

import java.util.List;

public interface RatingService {
    //create
    public Rating createRating(Rating rating);
    //get all ratings
    public List<Rating> getRatings();

    //get all ratings by userId
    public List<Rating> getRatingByUserId(String userId);
    //get all ratings by hotelId
    public List<Rating> fetRatingByHotelId(String id);
    //update the rating
    public Rating updateRating(Rating rating);

}
