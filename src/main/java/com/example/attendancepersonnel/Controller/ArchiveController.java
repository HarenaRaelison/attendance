package com.example.attendancepersonnel.Controller;

import com.example.attendancepersonnel.Entity.Archive;
import com.example.attendancepersonnel.Service.Implement.ArchiveService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/archive")
public class ArchiveController {
    private final ArchiveService archiveService;

    @PostMapping("/create")
    public ResponseEntity<Archive> createPersonnel(@RequestBody String path) {
        Archive createdArchive = archiveService.saveArchive(path);
        return ResponseEntity.ok(createdArchive);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Archive> getById(@PathVariable Long id) {
        Archive archive = archiveService.getArchive(id);
        if (archive != null) {
            return ResponseEntity.ok(archive);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
