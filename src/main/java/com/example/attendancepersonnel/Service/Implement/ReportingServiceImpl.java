package com.example.attendancepersonnel.Service.Implement;

import com.example.attendancepersonnel.DTO.DailyReportDTO;
import com.example.attendancepersonnel.Entity.Personnel;
import com.example.attendancepersonnel.Repository.PersonnelRepository;
import com.example.attendancepersonnel.Service.Interface.ReportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportingServiceImpl implements ReportingService {

    private final PersonnelRepository personnelRepository;
    private final PdfReportService pdfReportService;

    @Autowired
    public ReportingServiceImpl(PersonnelRepository personnelRepository, PdfReportService pdfReportService) {
        this.personnelRepository = personnelRepository;
        this.pdfReportService = pdfReportService;
    }

    @Override
    @Scheduled(cron = "0 0 9 * * *") // Exécute tous les jours
    public String generateDailyReport() {
        List<Personnel> personnelList = personnelRepository.findAll();
        LocalDate today = LocalDate.now();
        List<DailyReportDTO> dailyReportDTOList = new ArrayList<>();

        System.out.println("Rapport quotidien du " + today);

        for (Personnel personnel : personnelList) {
            Boolean statutPersonnel = personnel.isStatutPresence();
            dailyReportDTOList.add(new DailyReportDTO(personnel.getNom(), today, statutPersonnel));
            System.out.println("Nom : " + personnel.getNom() + ", Statut : " + (statutPersonnel ? "Présent" : "Absent"));
        }

        // Générer et sauvegarder le PDF
        try {
            byte[] pdfReport = pdfReportService.generateDailyReportPdf(dailyReportDTOList);
            return saveReportToFile(pdfReport, today); // Retourne le chemin du fichier sauvegardé
        } catch (IOException e) {
            e.printStackTrace(); // Log l'erreur correctement en fonction de votre configuration de journalisation
            return null; // Ou gérer l'erreur autrement selon vos besoins
        }
    }

    private String saveReportToFile(byte[] pdfReport, LocalDate date) throws IOException {
        String fileName = "Presence_" + date + ".pdf";
        Path path = Paths.get("D:\\archive", fileName);
        Files.createDirectories(path.getParent()); // Crée les répertoires parents si nécessaire
        Files.write(path, pdfReport);
        return path.toString(); // Retourne le chemin complet du fichier sauvegardé
    }
}
