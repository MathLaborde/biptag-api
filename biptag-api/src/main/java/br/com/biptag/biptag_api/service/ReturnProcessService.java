package br.com.biptag.biptag_api.service;

import br.com.biptag.biptag_api.model.Alert;
import br.com.biptag.biptag_api.model.Item;
import br.com.biptag.biptag_api.model.ReturnProcess;
import br.com.biptag.biptag_api.repository.AlertRepository;
import br.com.biptag.biptag_api.repository.ItemRepository;
import br.com.biptag.biptag_api.repository.ReturnProcessRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ReturnProcessService {

    private final ReturnProcessRepository repository;
    private final AlertRepository alertRepository;
    private final ItemRepository itemRepository;

    public ReturnProcessService(ReturnProcessRepository repository,
                                AlertRepository alertRepository,
                                ItemRepository itemRepository) {
        this.repository = repository;
        this.alertRepository = alertRepository;
        this.itemRepository = itemRepository;
    }

    public List<ReturnProcess> getAllReturnProcesses() {
        return repository.findAll();
    }

    // BLINDAGEM 1: Se o Android se perder e mandar ID 0, nós salvamos a pele dele enviando a última corrida!
    public ReturnProcess getReturnProcessById(Long id) {
        if (id == null || id == 0) {
            List<ReturnProcess> all = repository.findAll();
            if (!all.isEmpty()) {
                return all.get(all.size() - 1); // Retorna a mais recente
            }
        }
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Processo não encontrado com id: " + id));
    }

    // BLINDAGEM 2: Se a corrida não existir no banco, a API cria automaticamente em tempo real!
    public ReturnProcess getReturnProcessByAlertId(Long id) {
        ReturnProcess process = repository.findByAlertId(id);

        if (process == null) {
            ReturnProcess autoProcess = new ReturnProcess();
            autoProcess.setAlertId(id);
            autoProcess.setFoundReportId(1L); // Fallback de segurança
            autoProcess.setReturnType("home_delivery");
            autoProcess.setDeliveryFee(new BigDecimal("18.00"));
            autoProcess.setStatus("in_transit");

            // Gera o PIN de 4 dígitos automaticamente
            int randomPin = (int) (Math.random() * 9000) + 1000;
            autoProcess.setReturnCode(String.valueOf(randomPin));

            return repository.save(autoProcess);
        }
        return process;
    }

    public ReturnProcess createReturnProcess(ReturnProcess returnProcess) {
        if (returnProcess.getReturnCode() == null || returnProcess.getReturnCode().isEmpty()) {
            int randomPin = (int) (Math.random() * 9000) + 1000;
            returnProcess.setReturnCode(String.valueOf(randomPin));
        }
        if (returnProcess.getStatus() == null || returnProcess.getStatus().isEmpty()) {
            returnProcess.setStatus("in_transit");
        }
        if (returnProcess.getFoundReportId() == null || returnProcess.getFoundReportId() == 0) {
            returnProcess.setFoundReportId(1L);
        }
        return repository.save(returnProcess);
    }

    public ReturnProcess updateReturnProcess(Long id, ReturnProcess details) {
        ReturnProcess existingProcess = getReturnProcessById(id);
        existingProcess.setAlertId(details.getAlertId());
        existingProcess.setFoundReportId(details.getFoundReportId());
        existingProcess.setReturnType(details.getReturnType());
        existingProcess.setPartnerPointId(details.getPartnerPointId());
        existingProcess.setDeliveryFee(details.getDeliveryFee());
        existingProcess.setReturnCode(details.getReturnCode());
        existingProcess.setStatus(details.getStatus());
        return repository.save(existingProcess);
    }

    public ReturnProcess completeReturnProcess(Long processId) {
        ReturnProcess process = getReturnProcessById(processId);
        process.setStatus("completed");

        Alert alert = alertRepository.findById(process.getAlertId())
                .orElseThrow(() -> new RuntimeException("Alert not found"));
        alert.setStatus("resolved");
        alertRepository.save(alert);

        Item item = itemRepository.findById(alert.getItemId())
                .orElseThrow(() -> new RuntimeException("Item not found"));
        item.setStatus("safe");
        itemRepository.save(item);

        return repository.save(process);
    }
}