package com.example.attendancepersonnel.Service.Interface;

import com.example.attendancepersonnel.Entity.Personnel;

import java.util.List;

public interface PersonnelService {
    Personnel create(Personnel personnel);
    List<Personnel> read();
    Personnel update(Long id, Personnel personnel);
    void delete(Long id);
    int updateStatutPresenceTrue(Long id);
    int updateAllStatutPresenceFalse();
    int incrementCompteurMatin(Long id, int heures);
    int incrementCompteurMidi(Long id, int heures);
    int updatePresenceMatin(Long id, boolean presenceMatin, boolean contreAppelMatin);
    int updatePresenceMidi(Long id, boolean presenceMidi, boolean contreAppelMidi);
}
