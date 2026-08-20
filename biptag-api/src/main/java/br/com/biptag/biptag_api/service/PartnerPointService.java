package br.com.biptag.biptag_api.service;

import br.com.biptag.biptag_api.model.PartnerPoint;
import br.com.biptag.biptag_api.repository.PartnerPointRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartnerPointService {

    private final PartnerPointRepository repository;

    public PartnerPointService(PartnerPointRepository repository) {
        this.repository = repository;
    }

    // Equivale a police de SELECT do Supabase
    public List<PartnerPoint> getAllPartnerPoints() {
        return repository.findAll();
    }
}