package org.example.services;

import org.example.documents.Domestic;
import org.example.documents.dto.DomesticDTO;
import org.example.repositories.DomesticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DomesticServices implements CRUDServices<Domestic, DomesticDTO>{
    @Autowired
    private DomesticRepository repository;

    @Override
    public Domestic save(DomesticDTO domesticDTO) {
        return repository.save(new Domestic(domesticDTO));
    }

    @Override
    public List<Domestic> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Domestic> findByID(String id) {
        return repository.findById(id);
    }

    @Override
    public void deleteByID(String id) {
        repository.deleteById(id);
    }
}
