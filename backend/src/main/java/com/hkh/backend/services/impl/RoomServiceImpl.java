package com.hkh.backend.services.impl;

import com.hkh.backend.domain.dtos.RoomDto;
import com.hkh.backend.domain.entities.Room;
import com.hkh.backend.mappers.RoomMapper;
import com.hkh.backend.repositories.RoomRepository;
import com.hkh.backend.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    @Override
    public RoomDto addRoom(RoomDto roomDto) {
        if(roomDto.getRoomNumber() != null && roomRepository.findByRoomNumber(roomDto.getRoomNumber()).isPresent()) {
            throw new IllegalArgumentException("Room number already exists");
        }
        Room roomEntity = roomMapper.toEntity(roomDto);

        Room savedRoom = roomRepository.save(roomEntity);
        return roomMapper.toDto(savedRoom);
    }

    @Override
    public List<RoomDto> getAllRooms() {
        List<Room> roomsEntity = roomRepository.findAll();
        return roomsEntity.stream().map(roomMapper::toDto).toList();
    }

    @Override
    public RoomDto getRoomById(Integer id) {
        return roomRepository.findById(id)
                .map(roomMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Room not found"));
    }

    @Override
    public RoomDto updateRoomStatus(Integer id, String newStatus) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Room not found"));

        room.setStatus(newStatus);
        Room updatedRoom = roomRepository.save(room);

        return roomMapper.toDto(updatedRoom);
    }


}
