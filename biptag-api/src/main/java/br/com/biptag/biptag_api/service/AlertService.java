package br.com.biptag.biptag_api.service;

import br.com.biptag.biptag_api.model.Alert;
import br.com.biptag.biptag_api.repository.AlertRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertService {

    private final AlertRepository repository;

    public AlertService(AlertRepository repository) {
        this.repository = repository;
    }

    // Equivale a police de SELECT do Supabase
    public List<Alert> findAllAlerts() {
        return repository.findAll();
    }

    // Equivale a police de INSERT do Supabase
    public Alert createAlert(Alert alerta) {
        return repository.save(alerta);
    }
}
