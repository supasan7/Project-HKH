package com.hkh.backend.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "guests")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(unique = true)
    private String phone;

    @Column(name = "id_card_number", unique = true)
    private String idCardNumber;

    @Column(unique = true)
    private String email;

    @Column(columnDefinition = "TEXT")
    private String note;

    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL)
    private List<Booking> bookings;
}
