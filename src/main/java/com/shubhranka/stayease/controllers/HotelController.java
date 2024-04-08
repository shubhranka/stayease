package com.shubhranka.stayease.controllers;

import com.shubhranka.stayease.entities.Hotel;
import com.shubhranka.stayease.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping("")
    public List<Hotel> getHotels() {
        return hotelService.getHotels();
    }

    @PostMapping("")
    public String addHotel(@RequestBody Hotel hotel){
        return hotelService.addHotel(hotel);
    }

    @GetMapping("/{id}")
    public Hotel getHotelById(@PathVariable int id){
        return hotelService.getHotelById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteHotel(@PathVariable int id){
        return hotelService.deleteHotel(id);
    }
}
