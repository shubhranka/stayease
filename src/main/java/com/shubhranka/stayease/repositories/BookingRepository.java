package com.shubhranka.stayease.repositories;

import com.shubhranka.stayease.entities.Booking;
import com.shubhranka.stayease.entities.Hotel;
import com.shubhranka.stayease.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByHotel(Hotel hotel);

    boolean existsByUserAndHotel(User user, Hotel hotel);
}
