package com.example.attendancepersonnel.Repository;

import com.example.attendancepersonnel.Entity.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PersonnelRepository extends JpaRepository<Personnel, Long> {
    @Modifying
    @Query("UPDATE Personnel p SET p.statutPresence = true WHERE p.id = ?1")
    int updateStatutPresenceTrue(Long id);

    @Modifying
    @Query("UPDATE Personnel p SET p.statutPresence = false")
    int updateAllStatutPresenceFalse();

    @Modifying
    @Query("UPDATE Personnel p SET p.compteurMatin = p.compteurMatin + ?2 WHERE p.id = ?1")
    int incrementCompteurMatin(Long id, int heures);

    @Modifying
    @Query("UPDATE Personnel p SET p.compteurApresMidi = p.compteurApresMidi + ?2 WHERE p.id = ?1")
    int incrementCompteurMidi(Long id, int heures);

    @Modifying
    @Query("UPDATE Personnel p SET p.arriveeMatin = ?2, p.departMatin = ?3 WHERE p.id = ?1")
    int updatePresenceMatin(Long id, boolean presenceMatin, boolean contreAppelMatin);

    @Modifying
    @Query("UPDATE Personnel p SET p.arriveeApresMidi = ?2, p.departApresMidi = ?3 WHERE p.id = ?1")
    int updatePresenceMidi(Long id, boolean presenceMidi, boolean contreAppelMidi);
}
