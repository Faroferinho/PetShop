package org.example.controllers;

import org.example.documents.Domestic;
import org.example.documents.dto.DomesticDTO;
import org.example.safety.Constants;
import org.example.services.DomesticServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.constant.Constable;
import java.util.List;
import java.util.Optional;

@RestController
public class DomesticController implements CRUDController<Domestic, DomesticDTO> {
    @Autowired
    public DomesticServices services;

    @Override
    @PostMapping(Constants.DOMESTIC)
    public ResponseEntity<Domestic> create(@RequestHeader(value = "Authorization") String token, @RequestBody DomesticDTO domesticDTO) {
        Domestic d = services.save(domesticDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(d);
    }

    @Override
    @GetMapping(Constants.DOMESTIC)
    public ResponseEntity<List<Domestic>> findAll(@RequestHeader(value = "Authorization") String token) {
        return ResponseEntity.ok(services.findAll());
    }

    @Override
    @GetMapping(Constants.DOMESTIC + "/{id}")
    public ResponseEntity<Optional<Domestic>> findById(@RequestHeader(value = "Authorization") String token, @PathVariable String id) {
        return ResponseEntity.ok(services.findByID(id));
    }

    @Override
    @DeleteMapping(Constants.DOMESTIC + "/{id}")
    public ResponseEntity<Void> deleteById(@RequestHeader(value = "Authorization") String token, @PathVariable String id) {
        services.deleteByID(id);

        return ResponseEntity.noContent().build();
    }
}
