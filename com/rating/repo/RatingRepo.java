package com.rating.repo;

import com.rating.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepo extends JpaRepository<Rating, String> {

    //custom finder methods
    public List<Rating> findByUserId(String userId);
    public List<Rating> findByHotelId(String hotelId);
}
