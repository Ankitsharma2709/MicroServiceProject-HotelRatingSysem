package com.user.externalServices;

import com.user.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {
    @GetMapping("/ratings/users/{userId}")
    public List<Rating> getRatingsBYUser(@PathVariable("userId") String userId);
    @PostMapping("/ratings/")
    public Rating create(@RequestBody Rating rating);



}
