package br.com.biptag.biptag_api.controller;

import br.com.biptag.biptag_api.model.PartnerPoint;
import br.com.biptag.biptag_api.service.PartnerPointService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/partner-points")
public class PartnerPointController {

    private final PartnerPointService service;

    public PartnerPointController(PartnerPointService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PartnerPoint>> getAllPartnerPoints() {
        return ResponseEntity.ok(service.getAllPartnerPoints());
    }
}