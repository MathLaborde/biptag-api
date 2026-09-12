package br.com.biptag.biptag_api.controller;

import br.com.biptag.biptag_api.model.Alert;
import br.com.biptag.biptag_api.service.AlertService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/alerts")
public class AlertController {

    private final AlertService service;

    public AlertController(AlertService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Alert>> getAllAlerts() {
        return ResponseEntity.ok(service.findAllAlerts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alert> getAlertById(@PathVariable Long id) {
        return service.findAllAlerts().stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/with-reports")
    public ResponseEntity<List<Map<String, Object>>> getAllAlertsWithReports() {
        return ResponseEntity.ok(service.findAllAlertsWithReports());
    }

    @PostMapping
    public ResponseEntity<Alert> createAlert(@RequestBody Alert alerta) {
        Alert novoAlerta = service.createAlert(alerta);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAlerta);
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<Void> resolveAlert(@PathVariable Long id) {
        // Chama o serviço para resolver o alerta
        service.resolveAlert(id);
        return ResponseEntity.noContent().build();
    }
}
