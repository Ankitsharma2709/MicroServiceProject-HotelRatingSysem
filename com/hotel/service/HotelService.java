package com.hotel.service;

import com.hotel.entity.Hotel;

import java.util.List;

public interface HotelService {
    public Hotel addHotel (Hotel hotel);
    public List<Hotel> getAllHotels();
    public Hotel getSingleHotel(String id);
    public void deleteHotel(String id);
    public Hotel updateHotel(Hotel hotel);
}
