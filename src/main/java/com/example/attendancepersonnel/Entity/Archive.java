package com.example.attendancepersonnel.Entity;

import jakarta.persistence.*;


import java.util.Date;

@Entity
@Table(name = "archive")
public class Archive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "pdfRapportChemin",nullable = false)
    private String pdfRapportChemin;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public String getPdfRapportChemin() {
        return pdfRapportChemin;
    }

    public void setPdfRapportChemin(String pdfRapportChemin) {
        this.pdfRapportChemin = pdfRapportChemin;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
