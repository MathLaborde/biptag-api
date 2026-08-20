package br.com.biptag.biptag_api.service;

import br.com.biptag.biptag_api.model.ReturnProcess;
import br.com.biptag.biptag_api.repository.ReturnProcessRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReturnProcessService {

    private final ReturnProcessRepository repository;

    public ReturnProcessService(ReturnProcessRepository repository) {
        this.repository = repository;
    }

    // Equivale a police de SELECT do Supabase
    public List<ReturnProcess> getAllReturnProcesses() {
        return repository.findAll();
    }

    // Equivale a police de INSERT do Supabase
    public ReturnProcess createReturnProcess(ReturnProcess returnProcess) {
        return repository.save(returnProcess);
    }

    // Equivale a police de UPDATE do Supabase
    public ReturnProcess updateReturnProcess(Long id, ReturnProcess details) {
        ReturnProcess existingProcess = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Return process not found with id: " + id));

        existingProcess.setAlertId(details.getAlertId());
        existingProcess.setFoundReportId(details.getFoundReportId());
        existingProcess.setReturnType(details.getReturnType());
        existingProcess.setPartnerPointId(details.getPartnerPointId());
        existingProcess.setDeliveryFee(details.getDeliveryFee());
        existingProcess.setReturnCode(details.getReturnCode());
        existingProcess.setStatus(details.getStatus());

        return repository.save(existingProcess);
    }
}