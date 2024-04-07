package com.shubhranka.stayease.repositories;

import com.shubhranka.stayease.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
