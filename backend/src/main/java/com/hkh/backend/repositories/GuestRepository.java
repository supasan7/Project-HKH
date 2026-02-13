package com.hkh.backend.repositories;

import com.hkh.backend.domain.entities.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Integer> {
    Optional<Guest> findByIdCardNumber(String idCardNumber);
    Optional<Guest> findByPhone(String phone);
    Optional<Guest> findByEmail(String email);
}
