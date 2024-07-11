package com.example.attendancepersonnel.Service.Implement;

import com.example.attendancepersonnel.Entity.Personnel;
import com.example.attendancepersonnel.Repository.PersonnelRepository;
import com.example.attendancepersonnel.Service.Interface.PersonnelService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonnelServiceImpl implements PersonnelService {
    private final PersonnelRepository personnelRepository;

    @Override
    public Personnel create(Personnel personnel) {
        return personnelRepository.save(personnel);
    }

    @Override
    public List<Personnel> read() {
        return personnelRepository.findAll();
    }

    @Override
    public Personnel update(Long id, Personnel personnel) {
        return personnelRepository.findById(id)
                .map(p -> {
                    p.setNom(personnel.getNom());
                    p.setTypeContrat(personnel.getTypeContrat());
                    p.setStatutPresence(personnel.isStatutPresence());

                    return personnelRepository.save(p);
                })
                .orElseThrow(() -> new RuntimeException("Personnel not found"));
    }

    @Override
    public void delete(Long id) {
        personnelRepository.deleteById(id);
    }

    @Override
    public int updateStatutPresenceTrue(Long id) {
        return personnelRepository.updateStatutPresenceTrue(id);
    }

    @Override
    public int updateAllStatutPresenceFalse() {
        return personnelRepository.updateAllStatutPresenceFalse();
    }

    @Override
    public int incrementCompteurMatin(Long id, int heures) {
        return personnelRepository.incrementCompteurMatin(id, heures);
    }

    @Override
    public int incrementCompteurMidi(Long id, int heures) {
        return personnelRepository.incrementCompteurMidi(id, heures);
    }

    @Override
    public int updatePresenceMatin(Long id, boolean presenceMatin, boolean contreAppelMatin) {
        return personnelRepository.updatePresenceMatin(id, presenceMatin, contreAppelMatin);
    }

    @Override
    public int updatePresenceMidi(Long id, boolean presenceMidi, boolean contreAppelMidi) {
        return personnelRepository.updatePresenceMidi(id, presenceMidi, contreAppelMidi);
    }
}
