package org.example.controllers;

import org.example.documents.Domestic;
import org.example.documents.dto.DomesticDTO;
import org.example.services.DomesticServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class DomesticController implements CRUDController<Domestic, DomesticDTO> {
    @Autowired
    public DomesticServices services;

    @Override
    public ResponseEntity<Domestic> create(String token, DomesticDTO domesticDTO) {
        Domestic d = services.save(domesticDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(d);
    }

    @Override
    public ResponseEntity<List<Domestic>> findAll(String token) {
        return ResponseEntity.ok(services.findAll());
    }

    @Override
    public ResponseEntity<Optional<Domestic>> findById(String token, String id) {
        return ResponseEntity.ok(services.findByID(id));
    }

    @Override
    public ResponseEntity<Void> deleteById(String token, String id) {
        services.deleteByID(id);

        return ResponseEntity.noContent().build();
    }
}
