package br.com.biptag.biptag_api.service;

import br.com.biptag.biptag_api.model.FoundReport;
import br.com.biptag.biptag_api.repository.FoundReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoundReportService {

    @Autowired
    private FoundReportRepository foundReportRepository;

    // Equivale a police de SELECT do Supabase
    public List<FoundReport> findAllReports() {
        return foundReportRepository.findAll();
    }

    // Equivale a police de SELECT do Supabase puxando por ID
    public java.util.Optional<FoundReport> findById(Long id) {
        return foundReportRepository.findById(id);
    }

    // Equivale a police de SELECT do Supabase puxando pelo Alerta
    public java.util.Optional<FoundReport> findByAlertId(Long alertId) {
        return foundReportRepository.findByAlertId(alertId);
    }

    // Equivale a police de INSERT do Supabase
    public FoundReport createReport(FoundReport report) {
        return foundReportRepository.save(report);
    }
}