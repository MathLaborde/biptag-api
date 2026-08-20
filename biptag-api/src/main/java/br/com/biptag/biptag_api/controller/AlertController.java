package br.com.biptag.biptag_api.controller;

import br.com.biptag.biptag_api.model.Alert;
import br.com.biptag.biptag_api.service.AlertService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    public ResponseEntity<Alert> createAlert(@RequestBody Alert alerta) {
        Alert novoAlerta = service.createAlert(alerta);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAlerta);
    }
}
