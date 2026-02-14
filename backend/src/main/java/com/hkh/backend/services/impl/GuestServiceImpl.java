package com.hkh.backend.services.impl;

import com.hkh.backend.domain.dtos.GuestDto;
import com.hkh.backend.domain.entities.Guest;
import com.hkh.backend.mappers.GuestMapper;
import com.hkh.backend.repositories.GuestRepository;
import com.hkh.backend.services.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GuestServiceImpl implements GuestService {

    private final GuestRepository guestRepository;
    private final GuestMapper guestMapper;

    @Transactional
    @Override
    public GuestDto addGuest(GuestDto guestDto) {

        Guest guestEntity = guestMapper.toEntity(guestDto);

        if(guestDto.getEmail() != null && guestRepository.findByEmail(guestDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Guest with this email already exists");
        }

        if(guestDto.getPhone() != null && guestRepository.findByPhone(guestDto.getPhone()).isPresent()) {
            throw new IllegalArgumentException("Guest with this phone already exists");
        }

        Guest savedGuest = guestRepository.save(guestEntity);

        return guestMapper.toDto(savedGuest);
    }

    @Override
    public List<GuestDto> getAllGuests() {
        List<Guest> guestsEntity = guestRepository.findAll();
        return guestsEntity.stream().map(guestMapper::toDto).toList();
    }

    @Override
    public GuestDto findGuestById(Integer id) {
        return guestRepository.findById(id)
                .map(guestMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Guest not found"));
    }
}
