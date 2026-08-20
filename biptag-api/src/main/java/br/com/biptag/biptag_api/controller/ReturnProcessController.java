package br.com.biptag.biptag_api.controller;

import br.com.biptag.biptag_api.model.ReturnProcess;
import br.com.biptag.biptag_api.service.ReturnProcessService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/return-processes")
public class ReturnProcessController {

    private final ReturnProcessService service;

    public ReturnProcessController(ReturnProcessService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ReturnProcess>> getAllReturnProcesses() {
        return ResponseEntity.ok(service.getAllReturnProcesses());
    }

    @PostMapping
    public ResponseEntity<ReturnProcess> createReturnProcess(@RequestBody ReturnProcess returnProcess) {
        ReturnProcess newProcess = service.createReturnProcess(returnProcess);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProcess);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReturnProcess> updateReturnProcess(@PathVariable Long id, @RequestBody ReturnProcess details) {
        ReturnProcess updatedProcess = service.updateReturnProcess(id, details);
        return ResponseEntity.ok(updatedProcess);
    }
}
