package org.example.services;

import org.example.documents.DTO.MaintenanceDTO;
import org.example.documents.Maintenance;
import org.example.repositories.MaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceServices implements CRUDServices<Maintenance, MaintenanceDTO> {
    @Autowired
    private MaintenanceRepository repository;

    @Override
    public Maintenance save(MaintenanceDTO maintenanceDTO) {
        return repository.save(new Maintenance(maintenanceDTO));
    }

    @Override
    public List<Maintenance> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Maintenance> findByID(String id) {
        return repository.findById(id);
    }

    @Override
    public void deleteByID(String id) {
        repository.deleteById(id);
    }
}
