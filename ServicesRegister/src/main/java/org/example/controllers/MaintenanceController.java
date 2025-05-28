package org.example.controllers;

import org.example.documents.DTO.MaintenanceDTO;
import org.example.documents.Maintenance;
import org.example.safety.Constants;
import org.example.services.MaintenanceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MaintenanceController implements CRUDController<Maintenance, MaintenanceDTO> {
    @Autowired
    private MaintenanceServices services;

    @Override
    @PostMapping(Constants.MAINTENANCE)
    public ResponseEntity<Maintenance> create(@RequestHeader(value = "Authorization") String token, @RequestBody MaintenanceDTO maintenanceDTO) {
        Maintenance m = services.save(maintenanceDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(m);
    }

    @Override
    @GetMapping(Constants.MAINTENANCE)
    public ResponseEntity<List<Maintenance>> findAll(@RequestHeader(value = "Authorization") String token) {
        return ResponseEntity.ok(services.findAll());
    }

    @Override
    @GetMapping(Constants.MAINTENANCE + "/{id}")
    public ResponseEntity<Optional<Maintenance>> findById(@RequestHeader(value = "Authorization") String token, @PathVariable String id) {
        return ResponseEntity.ok(services.findByID(id));
    }

    @Override
    @DeleteMapping(Constants.MAINTENANCE + "/{id}")
    public ResponseEntity<Void> deleteById(@RequestHeader(value = "Authorization") String token, @PathVariable String id) {
        services.deleteByID(id);
        return ResponseEntity.noContent().build();
    }
}
