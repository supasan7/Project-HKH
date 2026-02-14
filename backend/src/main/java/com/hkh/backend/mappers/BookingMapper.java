package com.hkh.backend.mappers;

import com.hkh.backend.domain.dtos.BookingRequestDto;
import com.hkh.backend.domain.dtos.BookingResponseDto;
import com.hkh.backend.domain.entities.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    @Mapping(source = "room.id", target = "roomId")
    @Mapping(source = "guest.id", target = "guestId")
    BookingResponseDto toResponseDto(Booking booking);

    @Mapping(target = "room", ignore = true)
    @Mapping(target = "guest", ignore = true)
    @Mapping(target = "status", ignore = true)
    Booking toEntity(BookingRequestDto dto);
}
