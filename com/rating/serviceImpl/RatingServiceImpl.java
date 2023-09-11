package com.rating.serviceImpl;

import com.rating.entity.Rating;
import com.rating.repo.RatingRepo;
import com.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.RandomAccess;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepo ratingRepo;
    @Override
    public Rating createRating(Rating rating) {
        String id = UUID.randomUUID().toString();
        rating.setRatingId(id);
        return this.ratingRepo.save(rating);
    }

    @Override
    public List<Rating> getRatings() {
        return this.ratingRepo.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingRepo.findByUserId(userId);
    }

    @Override
    public List<Rating> fetRatingByHotelId(String id) {
        return ratingRepo.findByHotelId(id );
    }

    @Override
    public Rating updateRating(Rating rating) {
        return this.ratingRepo.save(rating);
    }
}
