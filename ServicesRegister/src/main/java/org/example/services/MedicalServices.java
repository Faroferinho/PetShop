package org.example.services;

import org.example.documents.DTO.MedicalDTO;
import org.example.documents.Medical;
import org.example.repositories.MedicalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalServices implements CRUDServices<Medical, MedicalDTO> {
    @Autowired
    private MedicalRepository repository;

    @Override
    public Medical save(MedicalDTO medicalDTO) {
        return repository.save(new Medical(medicalDTO));
    }

    @Override
    public List<Medical> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Medical> findByID(String id) {
        return repository.findById(id);
    }

    @Override
    public void deleteByID(String id) {
        repository.deleteById(id);
    }
}
