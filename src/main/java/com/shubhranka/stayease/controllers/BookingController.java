package com.shubhranka.stayease.controllers;

import com.shubhranka.stayease.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;
    @DeleteMapping("/bookings/{id}")
    public String deleteBooking(int id) {
        return bookingService.deleteBooking(id);
    }
}
