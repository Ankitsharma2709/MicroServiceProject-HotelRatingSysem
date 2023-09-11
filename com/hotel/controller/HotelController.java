package com.hotel.controller;

import com.hotel.customException.ResourceNotFound;
import com.hotel.entity.Hotel;
import com.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    private HotelService hotelService;

@PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/")
    public ResponseEntity<Hotel> addHotelDetails(@RequestBody Hotel hotel){
        return ResponseEntity.ok(this.hotelService.addHotel(hotel));

    }
    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping("/")
    public ResponseEntity<List<Hotel>> getAll(){
        return ResponseEntity.ok(this.hotelService.getAllHotels());
    }
    @PreAuthorize("hasAuthority('SCOPE_internal')")
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getSingle(@PathVariable("id") String id){
        if(id==null){
            throw new ResourceNotFound("Please enter the valid hotel id");
        }
        return ResponseEntity.ok(this.hotelService.getSingleHotel(id));
    }


    @DeleteMapping("/{id}")
    public void deleteSingleHotel(@PathVariable("id") String id){
        this.hotelService.deleteHotel(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hotel> updateHotel(@RequestBody Hotel hotel, @PathVariable("id") String id){

        if(id==null){
            throw new ResourceNotFound("Id shouldn't be null");

        }
        hotel.setId(id);
        return ResponseEntity.ok(this.hotelService.updateHotel(hotel));

    }
}
