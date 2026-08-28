package br.com.biptag.biptag_api.controller;

import br.com.biptag.biptag_api.model.FoundReport;
import br.com.biptag.biptag_api.service.FoundReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/found-reports")
public class FoundReportController {

    @Autowired
    private FoundReportService foundReportService;

    @GetMapping
    public ResponseEntity<List<FoundReport>> getAllReports() {
        List<FoundReport> reports = foundReportService.findAllReports();
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoundReport> getReportById(@PathVariable Long id) {
        return foundReportService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/alert/{alertId}")
    public ResponseEntity<FoundReport> getReportByAlertId(@PathVariable Long alertId) {
        return foundReportService.findByAlertId(alertId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FoundReport> createReport(@RequestBody FoundReport report) {
        FoundReport savedReport = foundReportService.createReport(report);
        return ResponseEntity.ok(savedReport);
    }
}