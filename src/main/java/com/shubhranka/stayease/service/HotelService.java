package com.shubhranka.stayease.service;

import com.shubhranka.stayease.entities.Hotel;
import com.shubhranka.stayease.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public String addHotel(Hotel hotel){
        hotelRepository.save(hotel);
        return "Hotel added";
    }

    public List<Hotel> getHotels(){
        return hotelRepository.findAll();
    }

    public Hotel getHotelById(int id){
        return hotelRepository.findById(id).orElse(null);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public String deleteHotel(int id){
        hotelRepository.deleteById(id);
        return "Hotel deleted";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public String updateHotel(int id, Hotel hotel){
        Hotel existingHotel = hotelRepository.findById(id).orElse(null);
        if(existingHotel == null){
            return "Hotel not found";
        }
        existingHotel.setName(hotel.getName());
        existingHotel.setRooms(hotel.getRooms());
        existingHotel.setLocation(hotel.getLocation());
        hotelRepository.save(existingHotel);
        return "Hotel updated";
    }
}
