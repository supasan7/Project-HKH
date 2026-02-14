package com.hkh.backend.repositories;

import com.hkh.backend.domain.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    List<Room> findByStatus(String status);
    Optional<Room> findByRoomNumber(String roomNumber);
    BigDecimal findPricePerNightById(Integer id);
}
