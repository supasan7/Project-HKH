package com.hkh.backend.services;

import com.hkh.backend.domain.dtos.RoomDto;

import java.util.List;

public interface RoomService {
    RoomDto addRoom(RoomDto roomDto);
    List<RoomDto> getAllRooms();
    RoomDto getRoomById(Integer id);
    RoomDto updateRoomStatus(Integer id, String newStatus);
}
