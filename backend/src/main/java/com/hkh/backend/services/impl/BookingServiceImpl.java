package com.hkh.backend.services.impl;

import com.hkh.backend.domain.dtos.BookingRequestDto;
import com.hkh.backend.domain.dtos.BookingResponseDto;
import com.hkh.backend.domain.entities.Booking;
import com.hkh.backend.domain.entities.Guest;
import com.hkh.backend.domain.entities.Room;
import com.hkh.backend.mappers.BookingMapper;
import com.hkh.backend.repositories.BookingRepository;
import com.hkh.backend.repositories.GuestRepository;
import com.hkh.backend.repositories.RoomRepository;
import com.hkh.backend.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;
    private final GuestRepository guestRepository;
    private final BookingMapper bookingMapper;

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

    @Transactional
    @Override
    public BookingResponseDto createBooking(BookingRequestDto bookingRequestDto) {
        if(!checkAvailability(bookingRequestDto.getRoomId(), bookingRequestDto.getCheckIn(), bookingRequestDto.getCheckOut())) {
            throw new IllegalArgumentException("This room already booked");
        }

        BigDecimal totalPrice = calculatePrice(bookingRequestDto.getRoomId(), bookingRequestDto.getCheckIn(), bookingRequestDto.getCheckOut());

        Room room = roomRepository.findById(bookingRequestDto.getRoomId())
                .orElseThrow(() -> new IllegalArgumentException("Room not found"));
        Guest guest = guestRepository.findById(bookingRequestDto.getGuestId())
                .orElseThrow(() -> new IllegalArgumentException("Guest not found"));

        String status = "PENDING";
        if (bookingRequestDto.getDepositAmount() != null) {
            if (bookingRequestDto.getDepositAmount().compareTo(totalPrice) >= 0) {
                status = "FULLY_PAID";
            } else if (bookingRequestDto.getDepositAmount().compareTo(totalPrice) < 0) {
                status = "DEPOSIT_PAID";
            }
        }
        Booking newBooking = bookingMapper.toEntity(bookingRequestDto);
        newBooking.setRoom(room);
        newBooking.setGuest(guest);
        newBooking.setTotalPrice(totalPrice);
        newBooking.setStatus(status);

        return bookingMapper.toResponseDto(bookingRepository.save(newBooking));
    }
}
