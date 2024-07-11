package com.example.attendancepersonnel.Controller;

import com.example.attendancepersonnel.Service.Interface.ReportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reporting")
public class ReportingController {

    private final ReportingService reportingService;

    @Autowired
    public ReportingController(ReportingService reportingService) {
        this.reportingService = reportingService;
    }

    @GetMapping("/generate-daily-report")
    public ResponseEntity<String> generateDailyReport() {
        String reportPath = reportingService.generateDailyReport();
        if (reportPath != null) {
            return ResponseEntity.ok(reportPath);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
