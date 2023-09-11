package com.hotel.servceImpl;

import com.hotel.customException.ResourceNotFound;
import com.hotel.entity.Hotel;
import com.hotel.repo.HotelRepo;
import com.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepo hotelRepo;
    @Override
    public Hotel addHotel(Hotel hotel) {
        String id = UUID.randomUUID().toString();
        hotel.setId(id);
        return hotelRepo.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepo.findAll();
    }

    @Override
    public Hotel getSingleHotel(String id) {
        return hotelRepo.findById(id).orElseThrow(()->new ResourceNotFound("Pleas eenter the valid user id the particular user cant be found !!"));
    }

    @Override
    public void deleteHotel(String id) {
        this.hotelRepo.deleteById(id);

    }

    @Override
    public Hotel updateHotel(Hotel hotel) {
        return this.hotelRepo.save(hotel);
    }
}
