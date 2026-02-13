package com.hkh.backend.services;

import com.hkh.backend.domain.dtos.GuestDto;

import java.util.List;

public interface GuestService {
    GuestDto addGuest(GuestDto guestDto);
    List<GuestDto> getAllGuests();
    GuestDto findGuestById(Integer id);
}
