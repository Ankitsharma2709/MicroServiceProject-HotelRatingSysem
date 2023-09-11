package com.rating.controllers;

import com.rating.entity.Rating;
import com.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.module.ResolutionException;
import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/")
    public ResponseEntity<Rating> create(@RequestBody Rating rating){
        return ResponseEntity.ok(this.ratingService.createRating(rating));
    }

    //get all
    @GetMapping("/")
    public ResponseEntity<List<Rating>> getRatings(){
        return ResponseEntity.ok(this.ratingService.getRatings());
    }
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingsBYUser(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(this.ratingService.getRatingByUserId(userId));
    }
    @GetMapping("/hotels/{id}")
    public ResponseEntity<List<Rating>> getRatingsByHotel(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.ratingService.fetRatingByHotelId(id));
    }

    @PutMapping("/{ratingId}")
    public ResponseEntity<Rating> updateTheRating(@RequestBody Rating rating , @PathVariable("ratingId") String ratingId){
        if(ratingId==null){
            throw new ResolutionException("Please ente the valid id");
        }
        rating.setRatingId(ratingId);
        return ResponseEntity.ok(this.ratingService.updateRating(rating));
    }
}
