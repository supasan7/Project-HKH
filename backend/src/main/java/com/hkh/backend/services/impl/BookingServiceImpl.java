package com.hkh.backend.services.impl;

import com.hkh.backend.repositories.BookingRepository;
import com.hkh.backend.repositories.RoomRepository;
import com.hkh.backend.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;

    @Override
    public BigDecimal calculatePrice(Integer roomId, LocalDate checkIn, LocalDate checkOut) {
        BigDecimal pricePerNight = roomRepository.findPricePerNightById(roomId);
        Long night = ChronoUnit.DAYS.between(checkIn, checkOut);
        if(night <= 0) {
            throw new IllegalArgumentException("Invalid booking date");
        }
        return pricePerNight.multiply(BigDecimal.valueOf(night));
    }

    @Override
    public Boolean checkAvailability(Integer roomId, LocalDate checkIn, LocalDate checkOut) {
        if(checkOut.isBefore(checkIn)) {
            throw new IllegalArgumentException("Invalid date time");
        }
        if(checkIn.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Check-in date cannot be in the past");
        }
        return bookingRepository.countOverlappingBookings(roomId, checkIn, checkOut) == 0;
    }
}
