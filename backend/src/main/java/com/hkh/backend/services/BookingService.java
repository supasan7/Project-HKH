package com.hkh.backend.services;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface BookingService {
    BigDecimal calculatePrice(Integer roomId, LocalDate checkIn, LocalDate checkOut);
    Boolean checkAvailability(Integer roomId, LocalDate checkIn, LocalDate checkOut);
}
