package com.hkh.backend.mappers;

import com.hkh.backend.domain.dtos.RoomDto;
import com.hkh.backend.domain.entities.Room;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    RoomDto toDto(Room room);
    Room toEntity(RoomDto roomDto);
}
