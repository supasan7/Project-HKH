package com.hkh.backend.repositories;

import com.hkh.backend.domain.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Query("SELECT COUNT(b) FROM Booking b " +
            "WHERE b.room.id = :roomId " +
            "AND b.checkIn < :checkOut " +
            "AND b.checkOut > :checkIn ")
    long countOverlappingBookings(Integer roomId, LocalDate checkIn, LocalDate checkOut);
}
