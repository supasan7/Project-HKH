package com.hkh.backend.mappers;

import com.hkh.backend.domain.dtos.GuestDto;
import com.hkh.backend.domain.entities.Guest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GuestMapper {
    GuestDto toDto(Guest guest);
    Guest toEntity(GuestDto guestDto);
}
