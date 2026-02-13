package com.hkh.backend.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {
    private Integer id;
    private String roomNumber;
    private String roomType;
    private BigDecimal pricePerNight;
    private String status;
}
