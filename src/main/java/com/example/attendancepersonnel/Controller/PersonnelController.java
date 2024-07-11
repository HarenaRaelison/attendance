package com.example.attendancepersonnel.Controller;

import com.example.attendancepersonnel.Entity.Personnel;
import com.example.attendancepersonnel.Service.Interface.PersonnelService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("customPersonnelController")
@AllArgsConstructor
@RequestMapping("/api/personnel")
public class PersonnelController {
    private final PersonnelService personnelService;

    @PostMapping("/create")
    public ResponseEntity<Personnel> createPersonnel(@RequestBody Personnel personnel) {
        Personnel createdPersonnel = personnelService.create(personnel);
        return ResponseEntity.ok(createdPersonnel);
    }

    @GetMapping
    public ResponseEntity<List<Personnel>> getAllPersonnel() {
        List<Personnel> personnels = personnelService.read();
        return ResponseEntity.ok(personnels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personnel> getPersonnelById(@PathVariable Long id) {
        return personnelService.read().stream()
                .filter(personnel -> personnel.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personnel> updatePersonnel(@PathVariable Long id, @RequestBody Personnel personnel) {
        try {
            Personnel updatedPersonnel = personnelService.update(id, personnel);
            return ResponseEntity.ok(updatedPersonnel);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePersonnel(@PathVariable Long id) {
        try {
            personnelService.delete(id);
            return ResponseEntity.ok("Personnel deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/statutPresence/true")
    public ResponseEntity<String> updateStatutPresenceTrue(@PathVariable Long id) {
        int rowsUpdated = personnelService.updateStatutPresenceTrue(id);
        if (rowsUpdated > 0) {
            return ResponseEntity.ok("Statut de présence mis à jour en true avec succès.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/statutPresence/false")
    public ResponseEntity<String> updateAllStatutPresenceFalse() {
        int rowsUpdated = personnelService.updateAllStatutPresenceFalse();
        return ResponseEntity.ok(rowsUpdated + " enregistrements mis à jour en false avec succès.");
    }

    @PutMapping("/{id}/incrementCompteurMatin")
    public ResponseEntity<String> incrementCompteurMatin(@PathVariable Long id, @RequestParam int heures) {
        int rowsUpdated = personnelService.incrementCompteurMatin(id, heures);
        if (rowsUpdated > 0) {
            return ResponseEntity.ok("Compteur matin mis à jour avec succès.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/incrementCompteurMidi")
    public ResponseEntity<String> incrementCompteurMidi(@PathVariable Long id, @RequestParam int heures) {
        int rowsUpdated = personnelService.incrementCompteurMidi(id, heures);
        if (rowsUpdated > 0) {
            return ResponseEntity.ok("Compteur midi mis à jour avec succès.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/presenceMatin")
    public ResponseEntity<String> updatePresenceMatin(@PathVariable Long id, @RequestParam boolean presenceMatin, @RequestParam boolean contreAppelMatin) {
        int rowsUpdated = personnelService.updatePresenceMatin(id, presenceMatin, contreAppelMatin);
        if (rowsUpdated > 0) {
            return ResponseEntity.ok("Présence matin mise à jour avec succès.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/presenceMidi")
    public ResponseEntity<String> updatePresenceMidi(@PathVariable Long id, @RequestParam boolean presenceMidi, @RequestParam boolean contreAppelMidi) {
        int rowsUpdated = personnelService.updatePresenceMidi(id, presenceMidi, contreAppelMidi);
        if (rowsUpdated > 0) {
            return ResponseEntity.ok("Présence midi mise à jour avec succès.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
