package com.shubhranka.stayease.service;

import com.shubhranka.stayease.entities.Booking;
import com.shubhranka.stayease.entities.Hotel;
import com.shubhranka.stayease.entities.User;
import com.shubhranka.stayease.exceptions.ValidationException;
import com.shubhranka.stayease.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class BookingService {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private BookingRepository bookingRepository;
    public String bookRoom(User user,int hotelId) {
        Hotel hotel = hotelService.getHotelById(hotelId);

        // Get all the booked rooms for this hotel
        List<Booking> bookings = bookingRepository.findByHotel(hotel);

        // Check if the user has already booked a room in this hotel
        boolean exists = bookingRepository.existsByUserAndHotel(user, hotel);
        if(exists){
            throw new ValidationException("User has already booked a room in this hotel");
        }

        // Check if the hotel has any rooms available
        if(bookings.size() >= hotel.getRooms()){
            throw new ValidationException("No rooms available in this hotel");
        }

        // Book the room
        Booking booking = new Booking();
        booking.setHotel(hotel);
        booking.setUser(user);
        bookingRepository.save(booking);

        return "Room booked";
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public String deleteBooking(int id) {
        bookingRepository.deleteById(id);
        return "Booking deleted";
    }
}
