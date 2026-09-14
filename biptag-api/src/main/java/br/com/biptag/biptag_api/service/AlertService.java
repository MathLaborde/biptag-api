package br.com.biptag.biptag_api.service;

import br.com.biptag.biptag_api.model.Alert;
import br.com.biptag.biptag_api.model.FoundReport;
import br.com.biptag.biptag_api.repository.AlertRepository;
import br.com.biptag.biptag_api.repository.FoundReportRepository;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.*;

@Service
public class AlertService {

    private final AlertRepository repository;
    private final FoundReportRepository reportRepository;
    private final ObjectMapper objectMapper;

    public AlertService(AlertRepository repository, FoundReportRepository reportRepository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.reportRepository = reportRepository;
        this.objectMapper = objectMapper;
    }

    public List<Alert> findAllAlerts() {
        return repository.findAll();
    }

    public Alert createAlert(Alert alerta) {
        return repository.save(alerta);
    }

    public List<Map<String, Object>> findAllAlertsWithReports() {
        // Busca apenas alertas com status "active"
        List<Alert> alerts = repository.findAllByStatus("active");

        return alerts.stream().map(alert -> {
            Optional<FoundReport> reportOpt = reportRepository.findByAlertId(alert.getId());

            @SuppressWarnings("unchecked")
            Map<String, Object> alertMap = objectMapper.convertValue(alert, LinkedHashMap.class);

            alertMap.put("report", reportOpt.orElse(null));

            return alertMap;
        }).toList();
    }

    // === NOVO MÉTODO PARA FINALIZAR A DEVOLUÇÃO E LIMPAR INVENTÁRIO ===
    public void resolveAlert(Long id) {
        Alert alert = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alerta não encontrado"));

        alert.setStatus("resolved");

        // Atualizado para usar o nome correto: getItemData()
        if (alert.getItemData() != null) {
            alert.getItemData().setStatus("active");
        }

        repository.save(alert);
    }
}