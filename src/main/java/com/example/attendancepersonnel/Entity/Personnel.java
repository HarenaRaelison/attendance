package com.example.attendancepersonnel.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Personnel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nom;

    @Column(name = "typeContrat",nullable = false)
    private TypeContrat typeContrat;

    @Column(name = "statutPresence",nullable = false)
    private boolean statutPresence = false;

    @Column(nullable = true)
    private LocalDateTime arriveeMatin;

    @Column(nullable = true)
    private LocalDateTime departMatin;

    @Column(nullable = true)
    private LocalDateTime arriveeApresMidi;

    @Column(nullable = true)
    private LocalDateTime departApresMidi;

    private int compteurMatin = 0;

    private int compteurApresMidi = 0;

    // getters and setters
}
