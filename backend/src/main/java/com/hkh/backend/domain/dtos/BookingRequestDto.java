package com.hkh.backend.domain.dtos;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequestDto {
    private Integer roomId;
    private Integer guestId;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private BigDecimal depositAmount;
}
