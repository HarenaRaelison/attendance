package com.example.attendancepersonnel.DTO;

import java.time.LocalDate;

public class DailyReportDTO {
    private String nom;
    private LocalDate date;
    private boolean statutPresence;

    // Constructeur
    public DailyReportDTO(String nom, LocalDate date, boolean statutPresence) {
        this.nom = nom;
        this.date = date;
        this.statutPresence = statutPresence;
    }

    // Getters et setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isStatutPresence() {
        return statutPresence;
    }

    public void setStatutPresence(boolean statutPresence) {
        this.statutPresence = statutPresence;
    }
}
