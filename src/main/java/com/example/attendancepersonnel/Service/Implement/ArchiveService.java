package com.example.attendancepersonnel.Service.Implement;

import com.example.attendancepersonnel.Entity.Archive;
import com.example.attendancepersonnel.Repository.ArchiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class ArchiveService {

    @Autowired
    private ArchiveRepository archiveRepository;

    public Archive saveArchive(String Path)  {
        Archive archive = new Archive();
        archive.setDate(new Date());
        archive.setPdfRapportChemin(Path);
        return archiveRepository.save(archive);
    }
    public Archive getArchive(Long id) {
        return archiveRepository.findById(id).orElse(null);
    }
}
