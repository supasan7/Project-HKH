package com.hkh.backend.controllers;

import com.hkh.backend.domain.dtos.GuestDto;
import com.hkh.backend.services.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/guests")
public class GuestController {

    private final GuestService guestService;

    @PostMapping
    public ResponseEntity<GuestDto> registerGuest(@RequestBody GuestDto guestDto) {
        GuestDto registedGuest = guestService.addGuest(guestDto);
        return new ResponseEntity<>(registedGuest, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GuestDto>> getAllGuests() {
        List<GuestDto> guests = guestService.getAllGuests();
        return new ResponseEntity<>(guests, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<GuestDto> getGuestById(@PathVariable Integer id) {
        GuestDto guest = guestService.getGuestById(id);
        return new ResponseEntity<>(guest, HttpStatus.OK);
    }
}
